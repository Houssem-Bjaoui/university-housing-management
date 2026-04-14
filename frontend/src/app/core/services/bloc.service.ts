import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Bloc } from '../../shared/models';

@Injectable({ providedIn: 'root' })
export class BlocService {
  private api = `${environment.apiUrl}/bloc`;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Bloc[]> {
    return this.http.get<Bloc[]>(`${this.api}/all`);
  }

  getById(id: number): Observable<Bloc> {
    return this.http.get<Bloc>(`${this.api}/${id}`);
  }

  // POST /bloc/add
  create(b: Bloc): Observable<Bloc> {
    return this.http.post<Bloc>(`${this.api}/add`, b);
  }

  // PUT /bloc/update
  update(b: Bloc): Observable<Bloc> {
    return this.http.put<Bloc>(`${this.api}/update`, b);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }

  // PUT /bloc/affecterChambres/{nomBloc} — body: list of chambre IDs
  affecterChambres(nomBloc: string, numChambres: number[]): Observable<Bloc> {
    return this.http.put<Bloc>(`${this.api}/affecterChambres/${nomBloc}`, numChambres);
  }

  affecterBlocAFoyer(nomBloc: string, nomFoyer: string): Observable<Bloc> {
    return this.http.put<Bloc>(`${this.api}/affecterBlocAFoyer/${nomBloc}/${nomFoyer}`, {});
  }
}
