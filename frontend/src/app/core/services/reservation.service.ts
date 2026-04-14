import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Reservation } from '../../shared/models';

@Injectable({ providedIn: 'root' })
export class ReservationService {
  private api = `${environment.apiUrl}/reservation`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Reservation[]> {
    return this.http.get<Reservation[]>(`${this.api}/all`);
  }

  getById(id: string): Observable<Reservation> {
    return this.http.get<Reservation>(`${this.api}/${id}`);
  }

  // POST /reservation/ajouter?numChambre=&cin=
  ajouter(numChambre: number, cin: number): Observable<Reservation> {
    const params = new HttpParams()
      .set('numChambre', numChambre)
      .set('cin', cin);
    return this.http.post<Reservation>(`${this.api}/ajouter`, {}, { params });
  }

  // DELETE /reservation/annuler?cinEtudiant=
  annuler(cinEtudiant: number): Observable<string> {
    const params = new HttpParams().set('cinEtudiant', cinEtudiant);
    return this.http.delete<string>(`${this.api}/annuler`, { params, responseType: 'text' as 'json' });
  }
}
