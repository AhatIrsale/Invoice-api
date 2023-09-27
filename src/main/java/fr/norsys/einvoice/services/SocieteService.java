package fr.norsys.einvoice.services;


import fr.norsys.einvoice.entities.Societe;
import fr.norsys.einvoice.reposities.SocieteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SocieteService {
    @Autowired
    public SocieteRepository societeRepository;


    public Societe save(Societe p) {
        return societeRepository.save(p);
    }

    public List<Societe> findAll() {
        return societeRepository.findAll();
    }
    public Optional<Societe> findById(UUID id) {
        return societeRepository.findById(id);
    }
    public List<Societe> findByType(String type) {
        return societeRepository.findByType(type);
    }

    public void update(Societe p) {
        societeRepository.save(p);
    }

    public void delete(Societe p) {societeRepository.delete(p);}

}
