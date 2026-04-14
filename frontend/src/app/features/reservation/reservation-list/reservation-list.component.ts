import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReservationService } from '../../../core/services';
import { Reservation } from '../../../shared/models';

@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css']
})
export class ReservationListComponent implements OnInit {
  reservations: Reservation[] = [];
  form: FormGroup;
  showModal = false;
  cancelCin: number | null = null;
  errorMsg = '';
  successMsg = '';

  constructor(private service: ReservationService, private fb: FormBuilder) {
    this.form = this.fb.group({
      numChambre: [null, [Validators.required, Validators.min(1)]],
      cin: [null, [Validators.required, Validators.min(1)]]
    });
  }

  ngOnInit() { this.load(); }

  load() {
    this.service.getAll().subscribe(data => this.reservations = data);
  }

  openForm() {
    this.form.reset();
    this.errorMsg = '';
    this.showModal = true;
  }

  closeForm() {
    this.showModal = false;
    this.form.reset();
  }

  save() {
    if (this.form.invalid) return;
    const { numChambre, cin } = this.form.value;
    this.service.ajouter(numChambre, cin).subscribe({
      next: () => { this.load(); this.closeForm(); this.successMsg = 'Reservation created!'; },
      error: (err) => { this.errorMsg = err?.error || 'Failed to create reservation.'; }
    });
  }

  cancelReservation() {
    if (!this.cancelCin) return;
    this.service.annuler(this.cancelCin).subscribe({
      next: () => { this.successMsg = 'Reservation cancelled!'; this.cancelCin = null; this.load(); },
      error: (err) => { this.errorMsg = err?.error || 'Failed to cancel reservation.'; }
    });
  }
}
