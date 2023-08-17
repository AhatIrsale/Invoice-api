package fr.norsys.einvoice.repository;

import fr.norsys.einvoice.entities.FactureModel;
import fr.norsys.einvoice.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FactureModelRepository extends JpaRepository<FactureModel, UUID> {
    Optional<FactureModel> findById(UUID id);

}
