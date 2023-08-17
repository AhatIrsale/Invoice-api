CREATE TABLE Item (
                      id UUID PRIMARY KEY,
                      type VARCHAR(255),
                      price DECIMAL(10, 2),
                      description VARCHAR(255),
                      quantite INT,
                      total DECIMAL(10, 2)
);
