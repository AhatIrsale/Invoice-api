package fr.norsys.einvoice.repository;

import fr.norsys.einvoice.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findById(UUID id);

}
