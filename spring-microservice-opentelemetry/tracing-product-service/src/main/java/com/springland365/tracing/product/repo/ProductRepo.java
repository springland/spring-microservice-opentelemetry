package com.springland365.tracing.product.repo;

import com.springland365.tracing.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<ProductEntity , Long > {
}
