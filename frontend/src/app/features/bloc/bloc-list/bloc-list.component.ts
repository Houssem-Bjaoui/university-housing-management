import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BlocService } from '../../../core/services';
import { Bloc } from '../../../shared/models';

@Component({
  selector: 'app-bloc-list',
  templateUrl: './bloc-list.component.html',
  styleUrls: ['./bloc-list.component.css']
})
export class BlocListComponent implements OnInit {
  blocs: Bloc[] = [];
  form: FormGroup;
  showModal = false;
  editMode = false;

  // For affecterChambres modal
  showAssignModal = false;
  showViewChambresModal = false;
  assignNomBloc = '';
  assignChambreIds = '';
  viewingBloc: Bloc | null = null;

  constructor(private service: BlocService, private fb: FormBuilder) {
    this.form = this.fb.group({
      idBloc: [null],
      nomBloc: ['', Validators.required],
      capaciteBloc: [null, [Validators.required, Validators.min(1)]]
    });
  }

  ngOnInit() { this.load(); }

  load() {
    this.service.getAll().subscribe(data => this.blocs = data);
  }

  openForm() {
    this.editMode = false;
    this.form.reset();
    this.showModal = true;
  }

  edit(item: Bloc) {
    this.editMode = true;
    this.form.patchValue({ idBloc: item.idBloc, nomBloc: item.nomBloc, capaciteBloc: item.capaciteBloc });
    this.showModal = true;
  }

  closeForm() {
    this.showModal = false;
    this.form.reset();
  }

  save() {
    if (this.form.invalid) return;
    const payload: Bloc = { nomBloc: this.form.value.nomBloc, capaciteBloc: this.form.value.capaciteBloc };
    if (this.editMode) payload.idBloc = this.form.value.idBloc;

    const req = this.editMode ? this.service.update(payload) : this.service.create(payload);
    req.subscribe(() => { this.load(); this.closeForm(); });
  }

  delete(id: number) {
    if (confirm('Delete this bloc?')) {
      this.service.delete(id).subscribe(() => this.load());
    }
  }

  openAssign(nomBloc: string) {
    this.assignNomBloc = nomBloc;
    this.assignChambreIds = '';
    this.showAssignModal = true;
  }

  closeAssign() {
    this.showAssignModal = false;
  }

  confirmAssign() {
    const ids = this.assignChambreIds
      .split(',')
      .map(s => parseInt(s.trim(), 10))
      .filter(n => !isNaN(n));

    if (ids.length === 0) { alert('Enter at least one chambre ID.'); return; }

    this.service.affecterChambres(this.assignNomBloc, ids).subscribe(() => {
      alert('Chambres assigned successfully!');
      this.closeAssign();
      this.load();
    });
  }

  openViewChambres(bloc: Bloc) {
    this.viewingBloc = bloc;
    this.showViewChambresModal = true;
  }

  closeViewChambres() {
    this.showViewChambresModal = false;
    this.viewingBloc = null;
  }
}
