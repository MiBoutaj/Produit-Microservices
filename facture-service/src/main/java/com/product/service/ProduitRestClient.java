package com.product.service;

import com.product.common.Produit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "PRODUIT")
public interface ProduitRestClient {

    @GetMapping(path = "/produits")
    PagedModel<Produit> pageProduit(@RequestParam(value = "page") int page,
                                    @RequestParam(value = "taille") int taille);

    @GetMapping(path = "/produits/{id}")
    Produit getProduitById(@PathVariable("id") Long id);
}
