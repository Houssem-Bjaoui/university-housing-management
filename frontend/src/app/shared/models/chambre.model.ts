export enum TypeChambre {
  SIMPLE = 'SIMPLE',
  DOUBLE = 'DOUBLE',
  TRIPLE = 'TRIPLE'
}

export interface Chambre {
  idChambre?: number;
  numeroChambre: number;
  typeC: TypeChambre;
  nomBloc?: string; // Nom du bloc auquel la chambre est affectée
}
