package com.springland365.tracing.product.service;

import com.springland365.tracing.dto.product.Product;
import com.springland365.tracing.product.entity.ProductEntity;
import com.springland365.tracing.product.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    protected final ProductRepo  repo ;

    public Optional<Product>  findProduct(Long id){


        return repo.findById(id).map(
                p -> Product.builder()
                        .id(p.getId())
                        .sku(p.getSku())
                        .name(p.getName())
                        .build()
        );


    }
}
