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

import com.gams.shoppingcart.api.entity.PurchaseOrder;
import com.gams.shoppingcart.api.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path="/order")
@CrossOrigin(origins="*")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService purchaseOrderService;

     /**
     * @return returns all orders
     */  
    @Operation(summary = "Get all orders", description = "Get all orders")
    @GetMapping(path="/all")
    public ResponseEntity<Iterable<PurchaseOrder>> getAllPurchaseOrders() {
        return ResponseEntity.ok(purchaseOrderService.getAllPurchaseOrders());
    }

     /**
     * @param id unique order identifier
     * @return returns the order
     */  
    @Operation(summary = "Get order by id", description = "Get order by id")
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> getPurchaseOrder(@PathVariable Long id) {
        Optional<PurchaseOrder> purchaseOrder = purchaseOrderService.getPurchaseOrder(id);
        if (purchaseOrder.isPresent()) {
            return ResponseEntity.ok(purchaseOrder.get());
        } else {
            logger.info("Not Found id:"+id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * @param purchaseOrder order to create
     * @return returns new order
     */    
    @Operation(summary = "Create new order", description = "Create new order")
    @PostMapping(path="/add")
    public ResponseEntity<PurchaseOrder> addNewPurchaseOrder(@RequestBody PurchaseOrder purchaseOrder) {
        PurchaseOrder newPurchaseOrder = purchaseOrderService.savePurchaseOrder(purchaseOrder);
        return ResponseEntity.ok(newPurchaseOrder);
    }

    /**
     * @param id unique order identifier
     * @param updatePurchaseOrder order to be updated
     * @return returns the updated order
     */        
    @Operation(summary = "Update order", description = "Update order")
    @PutMapping(path="/update/{id}")
    public ResponseEntity<PurchaseOrder> updatePurchaseOrder(@PathVariable Long id, @RequestBody PurchaseOrder updatePurchaseOrder) {
        PurchaseOrder updatedPurchaseOrder;
        try {
            updatedPurchaseOrder = purchaseOrderService.updatePurchaseOrder(id, updatePurchaseOrder);
            return ResponseEntity.ok(updatedPurchaseOrder);
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
    public String getIsLive() {
        return "its alive";
    }
}
