package com.product.repository;

import com.product.entity.Produit;
import com.product.projection.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.hateoas.PagedModel;

import java.util.List;

@RestResource
public interface ProductRepository extends JpaRepository<Produit,Long> {

}
