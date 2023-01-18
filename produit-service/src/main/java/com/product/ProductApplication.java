package com.product;


import com.product.entity.Produit;
import com.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.stream.Stream;

@SpringBootApplication
@EnableEurekaClient
public class ProductApplication {
    @Autowired
    RepositoryRestConfiguration repositoryRestConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class,args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository repository){
        return args -> {

            repositoryRestConfiguration.exposeIdsFor(Produit.class);
            Stream.of("Produit 1","Produit 2","Produit 3","Produit 4").forEach(t->
                    repository.save(new Produit(null,t,Math.random())));
            repository.findAll().forEach(p-> System.out.println("///////////////////"+p.getId()));

        };
    }
}
