package fr.norsys.einvoice.services;

import fr.norsys.einvoice.entities.Invoice;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IInvoiceService {
    Invoice save(Invoice o);
    List<Invoice> findAll();
    Optional<Invoice> findById(UUID id);
    void update(Invoice o);
    void delete(Invoice o);

}
