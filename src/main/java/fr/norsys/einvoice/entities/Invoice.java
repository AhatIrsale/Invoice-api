package fr.norsys.einvoice.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;



import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private String name;
    private String marque;
    @Column(columnDefinition = "DATE")
    private LocalDate dateDebut;

    @Column(columnDefinition = "DATE")
    private LocalDate dateFin;
    private boolean brouillon;
    private String status;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Societe client;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Societe responsable;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private FactureModel factureModel;
    @JsonManagedReference
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> items;

}
