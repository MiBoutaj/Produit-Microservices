package com.product.projection;

import com.product.entity.Produit;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Produit.class,name = "produit")
public interface ProductProjection {
    public Long getId();
    public String getNom();
    public Double getPrix();

}
