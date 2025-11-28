package com.springland365.tracing.cart.controller;

import com.springland365.tracing.cart.service.CartService;
import com.springland365.tracing.common.dto.Product;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/cart")
public class CartController
{


    protected final CartService  cartService;
    @PostMapping("/add/{id}")
    public Product addItem(@PathVariable(name = "id")  Long  id){

        log.info(" add product {}" , id);
        return cartService.addItem(id);
    }

}
