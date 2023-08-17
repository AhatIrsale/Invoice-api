package fr.norsys.einvoice.controllers;


import fr.norsys.einvoice.entities.FactureModel;
import fr.norsys.einvoice.services.FactureModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/FactureModels")
public class FactureModelController {
    @Autowired
    FactureModelService factureModelService;


    @PostMapping("/save")
    public FactureModel save(@RequestBody FactureModel p) {
        return factureModelService.save(p);
    }

    @GetMapping("/all")
    public List<FactureModel> findAll() {
        return factureModelService.findAll();
    }

    @GetMapping("/id/{id}")
    public Optional<FactureModel> findById(@PathVariable UUID id) {
        return factureModelService.findById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody FactureModel p) {
        factureModelService.update(p);
    }


}
