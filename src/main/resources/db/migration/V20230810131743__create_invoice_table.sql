CREATE TABLE Invoice (
                         id UUID PRIMARY KEY,
                         name VARCHAR(255),
                         marque VARCHAR(255),
                         date_debut DATE,
                         date_fin DATE,
                         brouillon BOOLEAN,
                         status VARCHAR(255),
                         client_id UUID,
                         responsable_id UUID,
                         item_id UUID,
                         facture_model_id UUID,
                         FOREIGN KEY (client_id) REFERENCES Societe(id),
                         FOREIGN KEY (client_id) REFERENCES Societe(id),
                         FOREIGN KEY (item_id) REFERENCES Item(id),
                         FOREIGN KEY (facture_model_id) REFERENCES facture_model(id)
);
