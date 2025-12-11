package com.springland365.tracing.product.controller;

import com.springland365.tracing.dto.product.Product;
import com.springland365.tracing.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    protected final ProductService  service ;

    @GetMapping("/{id}")
    public Optional<Product> findProduct(@PathVariable(name="id")  Long id){

        log.info("Find product by id {}" , id);
        return  service.findProduct(id);
    }

    @GetMapping()
    public List<Product> findAll(){

        log.info("Find all products");
        return this.service.findAllProducts();

    }

}
