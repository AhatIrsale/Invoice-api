package fr.norsys.einvoice.reposities;

import fr.norsys.einvoice.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, UUID> {
    Optional<Invoice> findById(UUID id);
    List<Invoice> findByDateDebutBetween(LocalDate dateD, LocalDate dateF);
}
