CREATE TABLE Invoice (
                         id UUID PRIMARY KEY,
                         name VARCHAR(255),
                         marque VARCHAR(255),
                         dateDebut DATE,
                         dateFin DATE,
                         brouillon BOOLEAN,
                         customer_id UUID,
                         FOREIGN KEY (customer_id) REFERENCES Customer (id)
);

