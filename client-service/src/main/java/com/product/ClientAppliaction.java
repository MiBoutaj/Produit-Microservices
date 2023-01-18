package com.product;


import com.product.entity.Client;
import com.product.repository.ClientRepository;
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
public class ClientAppliaction {


    @Autowired
    RepositoryRestConfiguration repositoryRestConfiguration;

    public static void main(String[] args) {

        SpringApplication.run(ClientAppliaction.class,args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ClientRepository repository){
        return args -> {

            repositoryRestConfiguration.exposeIdsFor(Client.class);
            Stream.of("Amine","Yassine","Aya").forEach(t->
                    repository.save(new Client(null,t,t+"@gamil.com")));

        };
    }
}
