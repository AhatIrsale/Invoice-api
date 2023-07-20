package fr.norsys.einvoice.services;

import fr.norsys.einvoice.invoice.Invoice;
import fr.norsys.einvoice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvoiceService implements IInvoiceService {
    @Autowired
    public InvoiceRepository invoiceRepository;
    @Override
    public Invoice save(Invoice p) {
        return invoiceRepository.save(p);
    }
    @Override
    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }
    public Optional<Invoice> findById(UUID id) {
        return invoiceRepository.findById(id);
    }
    @Override
    public void update(Invoice p) {
        invoiceRepository.save(p);
    }
    @Override
    public void delete(Invoice p) {
        invoiceRepository.delete(p);

    }
}
