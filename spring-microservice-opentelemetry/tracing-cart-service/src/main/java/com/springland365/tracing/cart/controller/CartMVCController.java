package com.springland365.tracing.cart.controller;

import com.springland365.tracing.cart.service.CartService;
import com.springland365.tracing.dto.product.Product;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
@SessionAttributes("cartItems")
public class CartMVCController {


    protected final CartService cartService;

    protected List<Product>  products ;

    @PostConstruct()
    public void init(){
        this.products = this.cartService.findAllProducts() ;

    }

    @ModelAttribute("cartItems")
    public List<Long> cartItems() {
        return new ArrayList<>();
    }
    @GetMapping("/")
    public String index(Model model){



        model.addAttribute("items" , Collections.emptyList());
        model.addAttribute("products" ,this.products);
        return "index" ;
    }

    @PostMapping("/cart/add")
    public String addItem(@RequestParam("productId") Long productId ,
                          @ModelAttribute("cartItems") List<Product> cartItems,
                          Model model){
        model.addAttribute("products" ,this.products);

        Optional<Product> product = products.stream().filter(p-> p.getId().equals(productId)).findFirst();
        cartItems.add(product.get());
        model.addAttribute("items" , cartItems);
        return "index" ;



    }
}
