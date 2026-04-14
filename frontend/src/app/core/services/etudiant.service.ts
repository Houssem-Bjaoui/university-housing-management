import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Etudiant } from '../../shared/models';

@Injectable({ providedIn: 'root' })
export class EtudiantService {
  private api = `${environment.apiUrl}/etudiant`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Etudiant[]> {
    return this.http.get<Etudiant[]>(`${this.api}/all`);
  }

  getById(id: number): Observable<Etudiant> {
    return this.http.get<Etudiant>(`${this.api}/${id}`);
  }

  // POST /etudiant/add
  create(e: Etudiant): Observable<Etudiant> {
    return this.http.post<Etudiant>(`${this.api}/add`, e);
  }

  // PUT /etudiant/update
  update(e: Etudiant): Observable<Etudiant> {
    return this.http.put<Etudiant>(`${this.api}/update`, e);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }

  getByCin(cin: number): Observable<Etudiant> {
    return this.http.get<Etudiant>(`${this.api}/byCin/${cin}`);
  }
}
