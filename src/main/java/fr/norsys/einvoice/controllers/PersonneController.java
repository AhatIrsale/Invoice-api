package fr.norsys.einvoice.controllers;


import fr.norsys.einvoice.entities.Personne;
import fr.norsys.einvoice.services.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/personnes")
public class PersonneController {
    @Autowired
    PersonneService personneService;


    @PostMapping("/save")
    public Personne save(@RequestBody Personne p) {
        return personneService.save(p);
    }

    @GetMapping("/all")
    public List<Personne> findAll() {
        return personneService.findAll();
    }

    @GetMapping("/id/{id}")
    public Optional<Personne> findById(@PathVariable UUID id) {
        return personneService.findById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Personne p) {
        personneService.update(p);
    }
}
