import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

// Layout
import { NavbarComponent } from './layout/navbar/navbar.component';
import { SidebarComponent } from './layout/sidebar/sidebar.component';

// Features
import { DashboardComponent } from './features/dashboard/dashboard.component';
import { UniversiteListComponent } from './features/universite/universite-list/universite-list.component';
import { FoyerListComponent } from './features/foyer/foyer-list/foyer-list.component';
import { BlocListComponent } from './features/bloc/bloc-list/bloc-list.component';
import { ChambreListComponent } from './features/chambre/chambre-list/chambre-list.component';
import { EtudiantListComponent } from './features/etudiant/etudiant-list/etudiant-list.component';
import { ReservationListComponent } from './features/reservation/reservation-list/reservation-list.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    SidebarComponent,
    DashboardComponent,
    UniversiteListComponent,
    FoyerListComponent,
    BlocListComponent,
    ChambreListComponent,
    EtudiantListComponent,
    ReservationListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
