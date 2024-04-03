package com.gams.shoppingcart.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gams.shoppingcart.api.entity.Product;
import com.gams.shoppingcart.api.service.ProductServiceClient;

import io.swagger.v3.oas.annotations.Operation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(path="/product")
@CrossOrigin(origins="*")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductServiceClient productServiceClient;

     /**
     * @return returns all products
     */    	
	@Operation(summary = "Get all products", description = "Get all products")
    @GetMapping(path="/all")
	public @ResponseBody Flux<Product> getAllProducts() {
		return productServiceClient.getAllProducts();
	}

     /**
     * @param id unique product identifier
     * @return returns the product
     */  	
	@Operation(summary = "Get product by id", description = "Get product by id")
    @GetMapping("/{id}")
 	public @ResponseBody Mono<Product> getProduct(@PathVariable Long id) {
		
		return productServiceClient.getProductById(id);
	} 
	
	

    /**
     * @return returns if the service is alive
     */	
	@Operation(summary = "Service is alive", description = "Service is alive")
	@GetMapping("/live")
	public String getIslive() {
		return "its alive";
	}
	

}
