package fr.norsys.einvoice.services;

import fr.norsys.einvoice.customer.Customer;
import fr.norsys.einvoice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    public CustomerRepository customerRepository;

    @Override
    public Customer save(Customer p) {
        return customerRepository.save(p);
    }
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
    public Optional<Customer> findById(UUID id) {
        return customerRepository.findById(id);
    }
    @Override
    public void update(Customer p) {
        customerRepository.save(p);
    }
    @Override
    public void delete(Customer p) {
        customerRepository.delete(p);

    }
}
