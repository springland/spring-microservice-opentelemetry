package com.springland365.tracing.cart.config;

import com.springland365.tracing.cart.client.ProductClient;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientConfig {

    @Value("${product-service-base-url}")
    String productServiceBaseUrl;
    @Bean
    public ProductClient  productClient(){
        WebClient webClient = WebClient.builder()
                .baseUrl(productServiceBaseUrl)
                .build();

        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build();

        return factory.createClient(ProductClient.class);
    }

}
