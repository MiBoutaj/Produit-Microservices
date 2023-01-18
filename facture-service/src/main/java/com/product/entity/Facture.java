package com.product.entity;


import com.product.common.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateFacture;


    @OneToMany(mappedBy = "facture")
    private Collection<ProduitArticle> listeProduits;

    private Long idClient;

    @Transient
    private Client client;
}
