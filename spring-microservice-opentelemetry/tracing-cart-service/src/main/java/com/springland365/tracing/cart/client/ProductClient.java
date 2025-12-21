package com.springland365.tracing.cart.client;

import com.springland365.tracing.dto.product.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange("/v1/products")
public interface ProductClient {



    @GetExchange("/{id}")
    Product findById(@PathVariable("id") Long id);

    @GetExchange
    List<Product> findAll();

}
