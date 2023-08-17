package fr.norsys.einvoice.services;


import fr.norsys.einvoice.entities.FactureModel;
import fr.norsys.einvoice.reposities.FactureModelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class FactureModelService {
    @Autowired
    public FactureModelRepository factureModelRepository;


    public FactureModel save(FactureModel p) {
        return factureModelRepository.save(p);
    }

    public List<FactureModel> findAll() {
        return factureModelRepository.findAll();
    }
    public Optional<FactureModel> findById(UUID id) {
        return factureModelRepository.findById(id);
    }

    public void update(FactureModel p) {
        factureModelRepository.save(p);
    }

    public void delete(FactureModel p) {factureModelRepository.delete(p);}
}
