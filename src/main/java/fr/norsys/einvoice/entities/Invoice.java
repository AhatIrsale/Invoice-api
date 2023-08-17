package fr.norsys.einvoice.invoice;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.norsys.einvoice.article.Article;
import fr.norsys.einvoice.customer.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


import java.time.LocalDate;
import java.util.Date;
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
    @OneToMany
    private List<Customer> customers;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "article_invoice",
            joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<Article> articles;




}
