package com.springland365.tracing.cart.service;

import com.springland365.tracing.cart.client.ProductClient;
import com.springland365.tracing.dto.product.Product;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartService {



    protected final ProductClient  productClient;

    protected final ObservationRegistry  registry;
    @Observed(name = "user.name",
            contextualName = "cart-service.findProductById",
            lowCardinalityKeyValues = {"userType", "userType2"})

    public Product findProductById(Long  id){

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
        Product product = productClient.findById(id);

        return product ;

    }

    public List<Product> findAllProducts(){

        return productClient.findAll() ;
    }

}
