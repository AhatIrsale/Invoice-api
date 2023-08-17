CREATE TABLE facture_model (
                              id UUID PRIMARY KEY,
                              logo VARCHAR(255),
                              color VARCHAR(255),
                              style VARCHAR(255),
                              client_id UUID,
                              responsable_id UUID,
                              FOREIGN KEY (client_id) REFERENCES Societe(id),
                              FOREIGN KEY (responsable_id) REFERENCES Societe(id)
);
