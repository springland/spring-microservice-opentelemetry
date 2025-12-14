package com.springland365.tracing.cart.controller;

import com.springland365.tracing.cart.model.CartItem;
import com.springland365.tracing.cart.service.CartService;
import com.springland365.tracing.dto.product.Product;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@SessionAttributes("cartItems")
public class CartMVCController {


    protected final CartService cartService;

    protected Map<Long , Product> productMap = new LinkedHashMap<>() ;

    @PostConstruct()
    public void init(){
        List<Product>  products =  this.cartService.findAllProducts() ;
        products.forEach(
                p ->  productMap.put(p.getId() , p)
        );

    }

    @ModelAttribute("cartItems")
    public Map<Long , CartItem> cartItems() {
        return new LinkedHashMap<>();
    }
    @GetMapping("/")
    public String index(Model model){



        model.addAttribute("items" , Collections.emptyList());
        model.addAttribute("products" ,getProducts());
        return "index" ;
    }

    protected List<Product>  getProducts(){
        return this.productMap.entrySet().stream().map( e -> e.getValue()).toList();
    }



    @PostMapping("/cart/add")
    public String addItem(@RequestParam("productId") Long productId ,
                          @ModelAttribute("cartItems") Map<Long , CartItem> cartItems,
                          Model model){
        model.addAttribute("products" ,getProducts());

        addItem(productId , cartItems);
        model.addAttribute("items" , cartItems);
        return "index" ;

    }

    protected List<CartItem> addItem( Long productId , Map<Long , CartItem> cartItemMap){

        Product product = productMap.get(productId);

        CartItem cartItem = cartItemMap.getOrDefault(productId ,
                CartItem.builder().product(product).qty(0).price(
                        getProductPrice(productId)
                ).build()

        );

        cartItem.setQty(cartItem.getQty()+1);
        return cartItemMap.entrySet().stream().map( e -> e.getValue()).toList();
    }

    protected BigDecimal  getProductPrice(Long productId){
        return BigDecimal.valueOf(1.0);
    }
}
