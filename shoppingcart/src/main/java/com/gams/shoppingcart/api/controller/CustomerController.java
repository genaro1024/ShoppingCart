package com.gams.shoppingcart.api.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gams.shoppingcart.api.entity.Customer;
import com.gams.shoppingcart.api.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;



@RestController
@RequestMapping(path="/customer")
@CrossOrigin(origins="*")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;


    /**
     * @return returns all customers
     */
    @Operation(summary = "Get all customers", description = "Get all customers")
    @GetMapping(path="/all")
	public ResponseEntity<Iterable<Customer>> getAllCustomers() {
		return ResponseEntity.ok(customerService.getAllCustomers());
	}
    
    /**
     * @param id unique customer identifier
     * @return returns the customer
     */
    @Operation(summary = "Get customers by id", description = "Get customers by id")
    @GetMapping("/{id}")
 	public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomer(id);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            logger.info("Not Found id:"+id);
            return ResponseEntity.notFound().build();
        }
	} 

    /**
     * @param customer cutomer to create
     * @return returns new customer
     */
    @Operation(summary = "Create new customer", description = "Create new customer")
	@PostMapping(path="/add")
	public ResponseEntity<Customer> addNewCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.saveCustomer(customer);
        return ResponseEntity.ok(newCustomer);
	}  
    
    /**
     * @param id unique customer identifier
     * @param updateCustomer customer to be updated
     * @return returns the updated customer
     */
    @Operation(summary = "Update customer", description = "Update customer")
	@PutMapping(path="/update/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id,@RequestBody Customer updateCustomer){
        Customer updatedCustomer;
        try {
            updatedCustomer = customerService.updateCustomer(id, updateCustomer);
            return ResponseEntity.ok(updatedCustomer);
        } catch (NotFoundException e) {
            logger.info("Not Found id:"+id, e);
            return ResponseEntity.notFound().build();
        }
        
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
