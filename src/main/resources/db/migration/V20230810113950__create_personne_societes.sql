CREATE TABLE Personne_Societe (
                                  personne_id UUID REFERENCES Personne(id),
                                  societe_id UUID REFERENCES Societe(id),
                                  PRIMARY KEY (personne_id, societe_id)
);
