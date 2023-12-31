package fr.norsys.einvoice.reposities;

import fr.norsys.einvoice.entities.Societe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SocieteRepository extends JpaRepository<Societe, UUID> {
    Optional<Societe> findById(UUID id);
    List<Societe> findByType(String type);
}
