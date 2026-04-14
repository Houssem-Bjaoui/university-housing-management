import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UniversiteService, FoyerService } from '../../../core/services';
import { Universite } from '../../../shared/models';

@Component({
  selector: 'app-universite-list',
  templateUrl: './universite-list.component.html',
  styleUrls: ['./universite-list.component.css']
})
export class UniversiteListComponent implements OnInit {
  universites: Universite[] = [];
  foyers: any[] = [];
  form: FormGroup;
  showModal = false;
  editMode = false;
  showAssignModal = false;
  assignUniversiteId: number = 0;
  assignFoyerId: number = 0;

  constructor(private service: UniversiteService, private foyerService: FoyerService, private fb: FormBuilder) {
    this.form = this.fb.group({
      idUniversite: [null],
      nomUniversite: ['', Validators.required],
      adresse: ['', Validators.required]
    });
  }

  ngOnInit() { 
    this.load();
    this.loadFoyers();
  }

  load() {
    this.service.getAll().subscribe(data => this.universites = data);
  }

  loadFoyers() {
    this.foyerService.getAll().subscribe(data => this.foyers = data);
  }

  openForm() {
    this.editMode = false;
    this.form.reset();
    this.showModal = true;
  }

  edit(item: Universite) {
    this.editMode = true;
    this.form.patchValue({ idUniversite: item.idUniversite, nomUniversite: item.nomUniversite, adresse: item.adresse });
    this.showModal = true;
  }

  closeForm() {
    this.showModal = false;
    this.form.reset();
  }

  save() {
    if (this.form.invalid) return;
    const payload: Universite = {
      nomUniversite: this.form.value.nomUniversite,
      adresse: this.form.value.adresse
    };
    if (this.editMode) payload.idUniversite = this.form.value.idUniversite;

    this.service.save(payload).subscribe(() => { this.load(); this.closeForm(); });
  }

  delete(id: number) {
    if (confirm('Delete this university?')) {
      this.service.delete(id).subscribe(() => this.load());
    }
  }

  openAssignFoyer(universite: Universite) {
    this.assignUniversiteId = universite.idUniversite!;
    this.assignFoyerId = universite.foyer?.idFoyer || 0;
    this.showAssignModal = true;
  }

  get availableFoyers() {
    // Show only foyers that are not assigned to any university, or the current one
    return this.foyers.filter(f => {
      const uni = this.universites.find(u => u.foyer?.idFoyer === f.idFoyer);
      return !uni || uni.idUniversite === this.assignUniversiteId;
    });
  }

  closeAssignModal() {
    this.showAssignModal = false;
  }

  confirmAssignFoyer() {
    if (!this.assignFoyerId) {
      alert('Please select a foyer');
      return;
    }
    // Backend expects: PUT /universite/affecterFoyer/{idFoyer}/{nomUniversite}
    // We need to get the university name first
    const uni = this.universites.find(u => u.idUniversite === this.assignUniversiteId);
    if (!uni) return;

    this.service.affecterFoyer(this.assignFoyerId, uni.nomUniversite).subscribe({
      next: () => {
        alert('Foyer assigned successfully!');
        this.closeAssignModal();
        this.load();
      },
      error: (err) => alert('Failed to assign foyer: ' + (err?.error || err?.message))
    });
  }
}
