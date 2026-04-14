import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Universite } from '../../shared/models';

@Injectable({ providedIn: 'root' })
export class UniversiteService {
  private api = `${environment.apiUrl}/universite`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Universite[]> {
    return this.http.get<Universite[]>(`${this.api}/all`);
  }

  getById(id: number): Observable<Universite> {
    return this.http.get<Universite>(`${this.api}/${id}`);
  }

  // POST /universite/addOrUpdate for both create and update
  save(u: Universite): Observable<Universite> {
    return this.http.post<Universite>(`${this.api}/addOrUpdate`, u);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }

  affecterFoyer(idFoyer: number, nomUniversite: string): Observable<Universite> {
    return this.http.put<Universite>(`${this.api}/affecterFoyer/${idFoyer}/${nomUniversite}`, {});
  }
}
