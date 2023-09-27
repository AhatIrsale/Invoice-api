CREATE TABLE Societe (
                         id UUID PRIMARY KEY,
                         name VARCHAR(255),
                         adresse VARCHAR(255),
                         phone VARCHAR(255),
                         mail VARCHAR(255),
                         type VARCHAR(255),
                         personne_id UUID,
                         FOREIGN KEY (personne_id) REFERENCES Personne(id) ON DELETE CASCADE ON UPDATE CASCADE
);
