import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  collapsed = false;

  ngOnInit() {
    window.addEventListener('toggleSidebar', (event: any) => {
      this.collapsed = event.detail;
    });
  }

  menuItems = [
    { icon: 'bi-speedometer2', label: 'Dashboard', route: '/dashboard' },
    { icon: 'bi-building', label: 'Universities', route: '/universite' },
    { icon: 'bi-house-door', label: 'Foyers', route: '/foyer' },
    { icon: 'bi-grid-3x3', label: 'Blocs', route: '/bloc' },
    { icon: 'bi-door-closed', label: 'Chambres', route: '/chambre' },
    { icon: 'bi-people', label: 'Students', route: '/etudiant' },
    { icon: 'bi-calendar-check', label: 'Reservations', route: '/reservation' }
  ];
}
