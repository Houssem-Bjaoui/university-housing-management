import { Chambre } from './chambre.model';

export interface Bloc {
  idBloc?: number;
  nomBloc: string;
  capaciteBloc: number;
  chambres?: Chambre[];
}
