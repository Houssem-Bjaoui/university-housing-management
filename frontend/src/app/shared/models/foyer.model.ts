import { Bloc } from './bloc.model';

export interface Foyer {
  idFoyer?: number;
  nomFoyer: string;
  capaciteFoyer: number;
  blocs?: Bloc[];
}
