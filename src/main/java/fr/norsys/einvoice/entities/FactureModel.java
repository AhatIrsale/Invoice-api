package fr.norsys.einvoice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FactureModel {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private String logo;
    private String color;
    private String style;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Societe client;
    @ManyToOne
    @JoinColumn(name = "responsable_id")
    private Societe responsable;

}
