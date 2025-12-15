package com.springland365.tracing.product.service;

import com.springland365.tracing.dto.product.Product;
import com.springland365.tracing.product.entity.ProductEntity;
import com.springland365.tracing.product.repo.ProductRepo;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j

public class ProductService {

    protected final ProductRepo  repo ;


    protected final MeterRegistry meterRegistry;

    protected Timer doFindProductTimer ;

    protected Timer doFindAllProductTimer ;

    public ProductService(ProductRepo  repo , MeterRegistry meterRegistry){
        this.repo = repo;
        this.meterRegistry = meterRegistry;
        this.doFindAllProductTimer =  meterRegistry.timer("product-service.doFindAllProducts");
        this.doFindProductTimer = meterRegistry.timer("product-service.doFindProduct");

    }
    public Optional<Product>  findProduct(Long id){



        return doFindProductTimer.record(() -> this.doFindProduct(id));


    }

    protected Optional<Product>  doFindProduct(Long id){
        return repo.findById(id).map(
                p -> Product.builder()
                        .id(p.getId())
                        .sku(p.getSku())
                        .name(p.getName())
                        .build()
        );

    }

    public List<Product> findAllProducts() {

        log.info(" find all products");
        return  doFindAllProductTimer.record( () -> doFindAllProducts());
    }

    protected List<Product> doFindAllProducts() {
        return repo.findAll().stream()
                .map( p ->
                        Product.builder()
                                .id(p.getId())
                                .sku(p.getSku())
                                .name(p.getName())
                                .build()
                )
                .toList();

    }
}
