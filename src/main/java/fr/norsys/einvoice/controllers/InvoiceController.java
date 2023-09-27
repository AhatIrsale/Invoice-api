package fr.norsys.einvoice.controllers;

import fr.norsys.einvoice.entities.Invoice;
import fr.norsys.einvoice.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    @Autowired
    private  InvoiceService invoiceService;

    @PostMapping("/save")
    public Invoice save(@RequestBody Invoice p) {
        return invoiceService.save(p);
    }

    @GetMapping("/all")
    public List<Invoice> findAll() {
        return invoiceService.findAll();
    }

    @GetMapping("/id/{id}")
    public Optional<Invoice> findById(@PathVariable UUID id) {
        return invoiceService.findById(id);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateInvoice(@RequestBody Invoice invoice) {
        try {
            invoiceService.update(invoice);
            return ResponseEntity.ok("Invoice updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating invoice");
        }
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Invoice p) {
        invoiceService.delete(p);
    }



}
