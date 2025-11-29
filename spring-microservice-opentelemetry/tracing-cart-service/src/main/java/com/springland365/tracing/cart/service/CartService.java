package com.springland365.tracing.cart.service;

import com.springland365.tracing.common.dto.Product;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartService {

    @Value("${catalog-service-base-url}")
    String catalogServiceBaseUrl;

    protected final WebClient webClient ;

    protected final ObservationRegistry  registry;
    @Observed(name = "user.name",
            contextualName = "cart-service.addItem",
            lowCardinalityKeyValues = {"userType", "userType2"})

    public Product addItem(  Long  id){

        log.info(" add product {}" , id);
        Product product = getProductObserved(id);
        return product ;

    }

    protected Product getProductObserved(Long id){

        return Observation.createNotStarted(
                "cart-service.getProduct",
                registry
                ).lowCardinalityKeyValue("type" , "service")
                .observe(() -> getProduct(id));

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
