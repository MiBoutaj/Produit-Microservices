package com.product.repository;

import com.product.entity.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface FactureRepo extends JpaRepository<Facture,Long> {
}
