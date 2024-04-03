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

import com.gams.shoppingcart.api.entity.Pay;
import com.gams.shoppingcart.api.service.PayService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path="/pay")
@CrossOrigin(origins="*")
public class PayController {

    private static final Logger logger = LoggerFactory.getLogger(PayController.class);

    @Autowired
    private PayService payService;

     /**
     * @return returns all pays
     */      
    @Operation(summary = "Get all pays", description = "Get all pays")
    @GetMapping(path="/all")
    public ResponseEntity<Iterable<Pay>> getAllPays() {
        return ResponseEntity.ok(payService.getAllPays());
    }

     /**
     * @param id unique pay identifier
     * @return returns the pay
     */  
    @Operation(summary = "Get pay by id", description = "Get pay by id")
    @GetMapping("/{id}")
    public ResponseEntity<Pay> getPay(@PathVariable Long id) {
        Optional<Pay> pay = payService.getPay(id);
        if (pay.isPresent()) {
            return ResponseEntity.ok(pay.get());
        } else {
            logger.info("Not Found id:"+id);
            return ResponseEntity.notFound().build();
        }
    } 
    /**
     * @param pay pay to create
     * @return returns new pay
     */  
    @Operation(summary = "Create new pay", description = "Create new pay")
    @PostMapping(path="/add")
    public ResponseEntity<Pay> addNewPay(@RequestBody Pay pay) {
        Pay newPay = payService.savePay(pay);
        return ResponseEntity.ok(newPay);
    }  
    

    /**
     * @param id unique pay identifier
     * @param updatePay pay to be updated
     * @return returns the updated pay
     */       
    @Operation(summary = "Update pay", description = "Update pay")
    @PutMapping(path="/update/{id}")
    public ResponseEntity<Pay> updatePay(@PathVariable Long id, @RequestBody Pay updatePay) {
        Pay updatedPay;
        try {
            updatedPay = payService.updatePay(id, updatePay);
            return ResponseEntity.ok(updatedPay);
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
