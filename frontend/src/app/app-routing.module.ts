import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './features/dashboard/dashboard.component';
import { UniversiteListComponent } from './features/universite/universite-list/universite-list.component';
import { FoyerListComponent } from './features/foyer/foyer-list/foyer-list.component';
import { BlocListComponent } from './features/bloc/bloc-list/bloc-list.component';
import { ChambreListComponent } from './features/chambre/chambre-list/chambre-list.component';
import { EtudiantListComponent } from './features/etudiant/etudiant-list/etudiant-list.component';
import { ReservationListComponent } from './features/reservation/reservation-list/reservation-list.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'universite', component: UniversiteListComponent },
  { path: 'foyer', component: FoyerListComponent },
  { path: 'bloc', component: BlocListComponent },
  { path: 'chambre', component: ChambreListComponent },
  { path: 'etudiant', component: EtudiantListComponent },
  { path: 'reservation', component: ReservationListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
