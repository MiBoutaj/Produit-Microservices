package com.product;


import com.product.common.Client;
import com.product.common.Produit;
import com.product.entity.Facture;
import com.product.entity.ProduitArticle;
import com.product.repository.FactureRepo;
import com.product.repository.ProArticleRepo;
import com.product.service.ClientRestClient;
import com.product.service.ProduitRestClient;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.PagedModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class FactureApplication  implements CommandLineRunner{

    ClientRestClient clientRestClient;
    ProduitRestClient restProduit;
    FactureRepo factureRepo;
    ProArticleRepo articleRep;

    RepositoryRestConfiguration repositoryRestConfiguration;

    public FactureApplication(ClientRestClient clientRestClient, ProduitRestClient restProduit, FactureRepo factureRepo, ProArticleRepo articleRep,RepositoryRestConfiguration repositoryRestConfiguration) {
        this.clientRestClient = clientRestClient;
        this.restProduit = restProduit;
        this.factureRepo = factureRepo;
        this.articleRep = articleRep;
        this.repositoryRestConfiguration=repositoryRestConfiguration;
    }

    public static void main(String[] args) {
        SpringApplication.run(FactureApplication.class,args);
    }

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Facture.class);
        repositoryRestConfiguration.exposeIdsFor(ProduitArticle.class);

        Client client = clientRestClient.getClientById(1L);

        Facture f = factureRepo.save(new Facture(null,new Date(),null, client.getId(),client));
        List<Produit> list = new ArrayList<>();
        PagedModel<Produit> listeProduitDB = restProduit.pageProduit(0,3);

        listeProduitDB.forEach(produit -> {
            ProduitArticle produitArticle = new ProduitArticle();
            produitArticle.setReference(produit.getId());
            produitArticle.setProduit(produit);
            produitArticle.setQuantite(1+new Random().nextInt(10));
            produitArticle.setFacture(f);
            produitArticle.setPrix(produitArticle.getQuantite()*produit.getPrix());
            articleRep.save(produitArticle);
        });

    }
}
