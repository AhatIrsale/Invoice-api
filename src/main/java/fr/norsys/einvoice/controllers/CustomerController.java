package fr.norsys.einvoice.controllers;

import fr.norsys.einvoice.customer.Customer;
import fr.norsys.einvoice.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;


    @PostMapping("/save")
    public Customer save(@RequestBody Customer p) {
        return customerService.save(p);
    }

    @GetMapping("/all")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/id/{id}")
    public Optional<Customer> findById(@PathVariable UUID id) {
        return customerService.findById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Customer p) {
        customerService.update(p);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Customer p) {
        customerService.delete(p);
    }
}
