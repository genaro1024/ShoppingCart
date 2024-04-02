package com.gams.shoppingcart.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gams.shoppingcart.api.entity.Product;
import com.gams.shoppingcart.api.service.ProductServiceClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping(path="/product")
@CrossOrigin(origins="*")
public class ProductController {

    @Autowired
    private ProductServiceClient productServiceClient;

    @GetMapping(path="/all")
	public @ResponseBody Flux<Product> getAllPersona() {
		return productServiceClient.getAllProducts();
	}
    
    @GetMapping("/{id}")
 	public @ResponseBody Mono<Product> getProduct(@PathVariable Long id) {
		
		return productServiceClient.getProductById(id);
	} 
	
	@GetMapping("/live")
	public String getIslive() {
		return "Is Live";
	}
	

}
