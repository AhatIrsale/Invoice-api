package fr.norsys.einvoice.invoice;

import fr.norsys.einvoice.Article.Article;
import fr.norsys.einvoice.Customer.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;


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
    private Date dateDebut;
    private Date dateFin;
    private boolean brouillon;
    @OneToMany
    private List<Customer> customers;
    @ManyToMany
    @JoinTable(
            name = "article_invoice",
            joinColumns = @JoinColumn(name = "invoice_id"),
            inverseJoinColumns = @JoinColumn(name = "article_id")
    )
    private List<Article> articles;




}
