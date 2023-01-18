package com.product.controller;


import com.product.common.Produit;
import com.product.entity.Facture;
import com.product.repository.FactureRepo;
import com.product.repository.ProArticleRepo;
import com.product.service.ClientRestClient;
import com.product.service.ProduitRestClient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@NoArgsConstructor
@CrossOrigin("*")
public class FactureController {

    @Autowired
    FactureRepo factureRepo;
   @Autowired
   ClientRestClient restClient;
    @Autowired
    ProduitRestClient produitRestClient;



    @GetMapping("/factures/{id}")
    public Facture getFacture(@PathVariable Long id){
        Facture facture = factureRepo.findById(id).get();
        facture.setClient(restClient.getClientById(facture.getIdClient()));
        facture.getListeProduits().forEach(p->{
            Produit produit = produitRestClient.getProduitById(p.getReference());
            p.setProduit(produit);
        });
        return facture;
    }

    @GetMapping("/factures")
    public List<Facture> getAll(){
        System.out.println("Liste  factures");
        return factureRepo.findAll();
    }


}
