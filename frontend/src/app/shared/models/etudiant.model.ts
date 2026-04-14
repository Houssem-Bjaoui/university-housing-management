export interface Etudiant {
  idEtudiant?: number;
  nomEt: string;
  prenomEt: string;
  cin: number;
  ecole: string;
  dateNaissance: string; // LocalDate from backend → ISO string "YYYY-MM-DD"
}
