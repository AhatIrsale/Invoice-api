package fr.norsys.einvoice.services;

import fr.norsys.einvoice.customer.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICustomerService {
    Customer save(Customer o);
    List<Customer> findAll();
    Optional<Customer> findById(UUID id);
    void update(Customer o);
    void delete(Customer o);
}
