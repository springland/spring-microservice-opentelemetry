package com.springland365.tracing.product.controller;

import com.springland365.tracing.dto.product.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
@Slf4j
public class ProductController {

    @GetMapping("/{id}")
    public Product  findById(@PathVariable(name="id")  Long id){

        log.info("test");
        return  Product.builder()
                        .id(id)
                        .name("dummy")
                        .description("dummy desc").build();

    }

    @GetMapping()
    public List<Product> findAll(){
        return Collections.emptyList();
    }

}
