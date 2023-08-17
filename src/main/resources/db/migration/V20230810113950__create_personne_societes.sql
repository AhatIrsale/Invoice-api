CREATE TABLE Personne_Societes (
                                  personne_id UUID REFERENCES Personne(id),
                                  societes_id UUID REFERENCES Societe(id),
                                  PRIMARY KEY (personne_id, societes_id)
);
