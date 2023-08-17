package fr.norsys.einvoice.services;


import fr.norsys.einvoice.entities.Item;
import fr.norsys.einvoice.reposities.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {
    @Autowired
    public ItemRepository itemRepository;


    public Item save(Item p) {
        return itemRepository.save(p);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }
    public Optional<Item> findById(UUID id) {
        return itemRepository.findById(id);
    }

    public void update(Item p) {
        itemRepository.save(p);
    }

    public void delete(Item p) {
        itemRepository.delete(p);

    }
}
