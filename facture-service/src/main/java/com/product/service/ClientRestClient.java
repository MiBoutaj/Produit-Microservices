package com.product.service;


import com.product.common.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CLIENT")
public interface ClientRestClient {

    @GetMapping(path = "/clients/{id}")
    Client getClientById(@PathVariable("id") Long id);

    @GetMapping(path = "/clients")
    List<Client> getClients();
}
