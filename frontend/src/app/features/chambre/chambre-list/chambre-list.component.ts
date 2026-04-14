import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ChambreService, BlocService } from '../../../core/services';
import { Chambre, TypeChambre, Bloc } from '../../../shared/models';

@Component({
  selector: 'app-chambre-list',
  templateUrl: './chambre-list.component.html',
  styleUrls: ['./chambre-list.component.css']
})
export class ChambreListComponent implements OnInit {
  chambres: Chambre[] = [];
  blocs: Bloc[] = [];
  form: FormGroup;
  showModal = false;
  editMode = false;
  typeChambreOptions = Object.values(TypeChambre);
  selectedBloc = '';

  constructor(
    private service: ChambreService,
    private blocService: BlocService,
    private fb: FormBuilder
  ) {
    this.form = this.fb.group({
      idChambre: [null],
      numeroChambre: [null, [Validators.required, Validators.min(1)]],
      typeC: ['', Validators.required]
    });
  }

  ngOnInit() {
    this.load();
    this.blocService.getAll().subscribe(data => this.blocs = data);
  }

  load() {
    this.service.getAll().subscribe(data => this.chambres = data);
  }

  filterByBloc() {
    if (this.selectedBloc) {
      this.service.getByBloc(this.selectedBloc).subscribe(data => this.chambres = data);
    } else {
      this.load();
    }
  }

  openForm() {
    this.editMode = false;
    this.form.reset();
    this.showModal = true;
  }

  edit(item: Chambre) {
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
    const payload: Chambre = { numeroChambre: this.form.value.numeroChambre, typeC: this.form.value.typeC };
    if (this.editMode) payload.idChambre = this.form.value.idChambre;

    const req = this.editMode ? this.service.update(payload) : this.service.create(payload);
    req.subscribe(() => { this.load(); this.closeForm(); });
  }

  delete(id: number) {
    if (confirm('Delete this chambre?')) {
      this.service.delete(id).subscribe(() => this.load());
    }
  }
}
