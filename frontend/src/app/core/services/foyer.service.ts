import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Foyer } from '../../shared/models';

@Injectable({ providedIn: 'root' })
export class FoyerService {
  private api = `${environment.apiUrl}/foyer`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Foyer[]> {
    return this.http.get<Foyer[]>(`${this.api}/all`);
  }

  getById(id: number): Observable<Foyer> {
    return this.http.get<Foyer>(`${this.api}/${id}`);
  }

  // POST /foyer/add
  create(f: Foyer): Observable<Foyer> {
    return this.http.post<Foyer>(`${this.api}/add`, f);
  }

  // PUT /foyer/update
  update(f: Foyer): Observable<Foyer> {
    return this.http.put<Foyer>(`${this.api}/update`, f);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }
}
