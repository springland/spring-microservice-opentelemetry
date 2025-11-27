package com.springland365.tracing.cart.controller;

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

    @Value("${catalog-service-base-url}")
    String catalogServiceBaseUrl;

    protected final WebClient webClient ;
    @PostMapping("/add/{id}")
    public Product addItem(@PathVariable(name = "id")  Long  id){

        log.info(" add product {}" , id);
        Product product = getProduct(id);
        return product ;

    }

    protected Product getProduct(Long id){
        log.info(" get product {}" , id);
        Product product = webClient.get()
                .uri(catalogServiceBaseUrl+"/v1/products/"+id)
                .retrieve()
                .bodyToMono(Product.class)
                .block();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error(e.getMessage() , e);
        }
        return product ;

    }
}
