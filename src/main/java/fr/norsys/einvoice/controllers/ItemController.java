package fr.norsys.einvoice.controllers;


import fr.norsys.einvoice.entities.Item;
import fr.norsys.einvoice.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/items")
public class ItemController {
    @Autowired
    ItemService itemService;


    @PostMapping("/save")
    public Item save(@RequestBody Item p) {
        return itemService.save(p);
    }

    @GetMapping("/all")
    public List<Item> findAll() {
        return itemService.findAll();
    }

    @GetMapping("/id/{id}")
    public Optional<Item> findById(@PathVariable UUID id) {
        return itemService.findById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody Item p) {
        itemService.update(p);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Item p) {
        itemService.delete(p);
    }
}
