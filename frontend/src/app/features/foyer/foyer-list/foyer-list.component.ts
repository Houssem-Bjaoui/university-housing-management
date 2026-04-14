import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FoyerService, BlocService } from '../../../core/services';
import { Foyer } from '../../../shared/models';

@Component({
  selector: 'app-foyer-list',
  templateUrl: './foyer-list.component.html',
  styleUrls: ['./foyer-list.component.css']
})
export class FoyerListComponent implements OnInit {
  foyers: Foyer[] = [];
  blocs: any[] = [];
  form: FormGroup;
  showModal = false;
  editMode = false;
  showAssignModal = false;
  showEditBlocsModal = false;
  assignFoyerName: string = '';
  assignBlocName: string = '';
  editingFoyer: Foyer | null = null;

  constructor(private service: FoyerService, private blocService: BlocService, private fb: FormBuilder) {
    this.form = this.fb.group({
      idFoyer: [null],
      nomFoyer: ['', Validators.required],
      capaciteFoyer: [null, [Validators.required, Validators.min(1)]]
    });
  }

  ngOnInit() { 
    this.load();
    this.loadBlocs();
  }

  load() {
    this.service.getAll().subscribe(data => this.foyers = data);
  }

  loadBlocs() {
    this.blocService.getAll().subscribe(data => this.blocs = data);
  }

  openForm() {
    this.editMode = false;
    this.form.reset();
    this.showModal = true;
  }

  edit(item: Foyer) {
    this.editMode = true;
    this.form.patchValue({ idFoyer: item.idFoyer, nomFoyer: item.nomFoyer, capaciteFoyer: item.capaciteFoyer });
    this.showModal = true;
  }

  closeForm() {
    this.showModal = false;
    this.form.reset();
  }

  save() {
    if (this.form.invalid) return;
    const payload: Foyer = { nomFoyer: this.form.value.nomFoyer, capaciteFoyer: this.form.value.capaciteFoyer };
    if (this.editMode) payload.idFoyer = this.form.value.idFoyer;

    const req = this.editMode ? this.service.update(payload) : this.service.create(payload);
    req.subscribe(() => { this.load(); this.closeForm(); });
  }

  delete(id: number) {
    if (confirm('Delete this foyer?')) {
      this.service.delete(id).subscribe(() => this.load());
    }
  }

  openAssignBloc(foyer: Foyer) {
    this.assignFoyerName = foyer.nomFoyer;
    this.assignBlocName = '';
    this.showAssignModal = true;
  }

  get availableBlocs() {
    // Show only blocs that are not assigned to any foyer
    const assignedBlocIds = this.foyers
      .flatMap(f => f.blocs || [])
      .map(b => b.idBloc);
    return this.blocs.filter(b => !assignedBlocIds.includes(b.idBloc));
  }

  openEditBlocs(foyer: Foyer) {
    this.editingFoyer = foyer;
    this.showEditBlocsModal = true;
  }

  closeEditBlocsModal() {
    this.showEditBlocsModal = false;
    this.editingFoyer = null;
  }

  removeBloc(blocName: string) {
    if (confirm(`Remove bloc "${blocName}" from this foyer?`)) {
      // Note: Backend doesn't have an unassign endpoint, so this is a UI-only feature
      // You would need to add a backend endpoint like: DELETE /bloc/desaffecterBlocDeFoyer/{nomBloc}
      alert('Backend endpoint for removing bloc not available. Please contact administrator.');
    }
  }

  closeAssignModal() {
    this.showAssignModal = false;
  }

  confirmAssignBloc() {
    if (!this.assignBlocName) {
      alert('Please select a bloc');
      return;
    }
    // Backend expects: PUT /bloc/affecterBlocAFoyer/{nomBloc}/{nomFoyer}
    this.blocService.affecterBlocAFoyer(this.assignBlocName, this.assignFoyerName).subscribe({
      next: () => {
        alert('Bloc assigned successfully!');
        this.closeAssignModal();
        this.load();
      },
      error: (err) => alert('Failed to assign bloc: ' + (err?.error || err?.message))
    });
  }
}
