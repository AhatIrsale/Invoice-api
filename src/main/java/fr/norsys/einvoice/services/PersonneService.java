package fr.norsys.einvoice.services;


import fr.norsys.einvoice.entities.Personne;
import fr.norsys.einvoice.reposities.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonneService {
    @Autowired
    public PersonneRepository personneRepository;


    public Personne save(Personne p) {
        return personneRepository.save(p);
    }

    public List<Personne> findAll() {
        return personneRepository.findAll();
    }
    public Optional<Personne> findById(UUID id) {
        return personneRepository.findById(id);
    }

    public void update(Personne p) {
        personneRepository.save(p);
    }

    public void delete(Personne p) {personneRepository.delete(p);}
}
