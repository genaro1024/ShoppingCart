package com.gams.shoppingcart.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.gams.shoppingcart.api.entity.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceClient {

    private final String baseUrl = "https://fakestoreapi.com";
    private final WebClient webClient;


    public ProductServiceClient() {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    public Mono<Product> getProductById(Long id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/products/{id}").build(id))
                .retrieve()
                .bodyToMono(Product.class);
    } 
    
    public Flux<Product> getAllProducts(){
        return webClient.get()
                .uri("/products")
                .retrieve()
                .bodyToFlux(Product.class);
    }

}
