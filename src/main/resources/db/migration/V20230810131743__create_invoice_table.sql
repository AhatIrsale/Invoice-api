CREATE TABLE Invoice (
                         id UUID PRIMARY KEY,
                         name VARCHAR(255),
                         marque VARCHAR(255),
                         dateDebut DATE,
                         dateFin DATE,
                         brouillon BOOLEAN,
                         status VARCHAR(255),
                         client_id UUID,
                         responsable_id UUID,
                         factureModel_id UUID,
                         FOREIGN KEY (client_id) REFERENCES Societe(id),
                         FOREIGN KEY (responsable_id) REFERENCES Societe(id),
                         FOREIGN KEY (factureModel_id) REFERENCES FactureModel(id)
);
