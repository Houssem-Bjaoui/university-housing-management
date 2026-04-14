import { Component, OnInit } from '@angular/core';
import { UniversiteService, FoyerService, BlocService, ChambreService, EtudiantService, ReservationService } from '../../core/services';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  stats = {
    universites: 0,
    foyers: 0,
    blocs: 0,
    chambres: 0,
    etudiants: 0,
    reservations: 0
  };

  constructor(
    private universiteService: UniversiteService,
    private foyerService: FoyerService,
    private blocService: BlocService,
    private chambreService: ChambreService,
    private etudiantService: EtudiantService,
    private reservationService: ReservationService
  ) {}

  ngOnInit() {
    this.loadStats();
  }

  loadStats() {
    this.universiteService.getAll().subscribe(data => this.stats.universites = data.length);
    this.foyerService.getAll().subscribe(data => this.stats.foyers = data.length);
    this.blocService.getAll().subscribe(data => this.stats.blocs = data.length);
    this.chambreService.getAll().subscribe(data => this.stats.chambres = data.length);
    this.etudiantService.getAll().subscribe(data => this.stats.etudiants = data.length);
    this.reservationService.getAll().subscribe(data => this.stats.reservations = data.length);
  }
}
