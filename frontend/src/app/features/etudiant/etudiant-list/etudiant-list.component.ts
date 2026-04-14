import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EtudiantService } from '../../../core/services';
import { Etudiant } from '../../../shared/models';

@Component({
  selector: 'app-etudiant-list',
  templateUrl: './etudiant-list.component.html',
  styleUrls: ['./etudiant-list.component.css']
})
export class EtudiantListComponent implements OnInit {
  etudiants: Etudiant[] = [];
  form: FormGroup;
  showModal = false;
  editMode = false;
  showViewModal = false;
  selectedStudent: Etudiant | null = null;

  constructor(private service: EtudiantService, private fb: FormBuilder) {
    this.form = this.fb.group({
      idEtudiant: [null],
      prenomEt: ['', Validators.required],
      nomEt: ['', Validators.required],
      cin: [null, [Validators.required, Validators.min(1)]],
      ecole: ['', Validators.required],
      dateNaissance: ['', Validators.required]
    });
  }

  ngOnInit() { this.load(); }

  load() {
    this.service.getAll().subscribe(data => this.etudiants = data);
  }

  openForm() {
    this.editMode = false;
    this.form.reset();
    this.showModal = true;
  }

  edit(item: Etudiant) {
    this.editMode = true;
    this.form.patchValue(item);
    this.showModal = true;
  }

  closeForm() {
    this.showModal = false;
    this.form.reset();
  }

  save() {
    if (this.form.invalid) return;
    const v = this.form.value;
    const payload: Etudiant = {
      prenomEt: v.prenomEt,
      nomEt: v.nomEt,
      cin: v.cin,
      ecole: v.ecole,
      dateNaissance: v.dateNaissance  // "YYYY-MM-DD" string → LocalDate on backend
    };
    if (this.editMode) payload.idEtudiant = v.idEtudiant;

    const req = this.editMode ? this.service.update(payload) : this.service.create(payload);
    req.subscribe(() => { this.load(); this.closeForm(); });
  }

  delete(id: number) {
    if (confirm('Delete this student?')) {
      this.service.delete(id).subscribe(() => this.load());
    }
  }

  viewDetails(id: number) {
    this.service.getById(id).subscribe(student => {
      this.selectedStudent = student;
      this.showViewModal = true;
    });
  }

  closeViewModal() {
    this.showViewModal = false;
    this.selectedStudent = null;
  }
}
