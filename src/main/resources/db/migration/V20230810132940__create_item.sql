CREATE TABLE Item (
                      id UUID PRIMARY KEY,
                      type VARCHAR(255),
                      price DECIMAL(10, 2),
                      description VARCHAR(255),
                      quantite INT,
                      total DECIMAL(10, 2),
                      invoice_id UUID,
                      FOREIGN KEY (invoice_id) REFERENCES Invoice(id) ON UPDATE CASCADE ON DELETE CASCADE

);
