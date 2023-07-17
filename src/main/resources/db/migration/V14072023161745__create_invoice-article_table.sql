CREATE TABLE article_invoice (
                                 invoice_id UUID,
                                 article_id UUID,
                                 PRIMARY KEY (invoice_id, article_id),
                                 FOREIGN KEY (invoice_id) REFERENCES Invoice (id),
                                 FOREIGN KEY (article_id) REFERENCES Article (id)
);
