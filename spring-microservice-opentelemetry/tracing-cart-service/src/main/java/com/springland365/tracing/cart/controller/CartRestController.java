package com.springland365.tracing.cart.controller;

import com.springland365.tracing.cart.service.CartService;
import com.springland365.tracing.dto.product.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/cart")
public class CartRestController
{


    protected final CartService  cartService;
    @PostMapping("/add/{id}")
    public Product addItem(@PathVariable(name = "id")  Long  id){

        log.info(" add product {}" , id);
        return cartService.findProductById(id);
    }

}
