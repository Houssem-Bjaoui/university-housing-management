import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Chambre, TypeChambre } from '../../shared/models';

@Injectable({ providedIn: 'root' })
export class ChambreService {
  private api = `${environment.apiUrl}/chambre`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Chambre[]> {
    return this.http.get<Chambre[]>(`${this.api}/all`);
  }

  getById(id: number): Observable<Chambre> {
    return this.http.get<Chambre>(`${this.api}/${id}`);
  }

  // POST /chambre/add
  create(c: Chambre): Observable<Chambre> {
    return this.http.post<Chambre>(`${this.api}/add`, c);
  }

  // PUT /chambre/update
  update(c: Chambre): Observable<Chambre> {
    return this.http.put<Chambre>(`${this.api}/update`, c);
  }

  // DELETE /chambre/delete/{id}
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/delete/${id}`);
  }

  getByBloc(nomBloc: string): Observable<Chambre[]> {
    return this.http.get<Chambre[]>(`${this.api}/byBloc/${nomBloc}`);
  }

  countByTypeAndBloc(type: TypeChambre, idBloc: number): Observable<number> {
    const params = new HttpParams().set('type', type).set('idBloc', idBloc);
    return this.http.get<number>(`${this.api}/countByTypeAndBloc`, { params });
  }
}
