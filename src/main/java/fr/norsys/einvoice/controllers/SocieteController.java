package fr.norsys.einvoice.controllers;


import fr.norsys.einvoice.entities.Invoice;
import fr.norsys.einvoice.entities.Societe;
import fr.norsys.einvoice.services.SocieteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/societes")
public class SocieteController {
    @Autowired
    SocieteService societeService;


    @PostMapping("/save")
    public Societe save(@RequestBody Societe p) {
        return societeService.save(p);
    }

    @GetMapping("/all")
    public List<Societe> findAll() {
        return societeService.findAll();
    }

    @GetMapping("/id/{id}")
    public Optional<Societe> findById(@PathVariable UUID id) {
        return societeService.findById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Societe p) {
        societeService.update(p);
    }
   @GetMapping("/type/{type}")
    public  List<Societe> findByType(@PathVariable String type)
    {
        return societeService.findByType(type);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestBody Societe p) {
        societeService.delete(p);
    }
}
