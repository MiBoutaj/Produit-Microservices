package com.product.repository;

import com.product.entity.ProduitArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface ProArticleRepo extends JpaRepository<ProduitArticle,Long> {
}
