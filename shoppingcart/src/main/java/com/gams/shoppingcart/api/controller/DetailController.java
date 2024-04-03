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
import com.gams.shoppingcart.api.entity.Detail;
import com.gams.shoppingcart.api.service.DetailService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path="/detail")
@CrossOrigin(origins="*")
public class DetailController {

    private static final Logger logger = LoggerFactory.getLogger(DetailController.class);

    @Autowired
    private DetailService detailService;

     /**
     * @return returns all details
     */   
    @Operation(summary = "Get all details", description = "Get all details")
    @GetMapping(path="/all")
    public ResponseEntity<Iterable<Detail>> getAllDetails() {
        return ResponseEntity.ok(detailService.getAllDetails());
    }


     /**
     * @param id unique order identifier
     * @return returns details by purchase order
     */   
    @Operation(summary = "Get all details by order", description = "Get all details by order")
    @GetMapping(path="/order/{id}")
    public ResponseEntity<Iterable<Detail>> getAllDetailsByOrder(@PathVariable Long id) {
        return ResponseEntity.ok(detailService.getAllDetailsByOrderId(id));
    }    
    
     /**
     * @param id unique detail identifier
     * @return returns the detail
     */   
    @Operation(summary = "Get all detail by id", description = "Get all detail by id")
    @GetMapping("/{id}")
    public ResponseEntity<Detail> getDetail(@PathVariable Long id) {
        Optional<Detail> detail = detailService.getDetail(id);
        if (detail.isPresent()) {
            return ResponseEntity.ok(detail.get());
        } else {
            logger.info("Not Found id:"+id);
            return ResponseEntity.notFound().build();
        }
    } 

    /**
     * @param detail detail to create
     * @return returns new detail
     */
    @Operation(summary = "Create new detail", description = "Create new detail")
    @PostMapping(path="/add")
    public ResponseEntity<Detail> addNewDetail(@RequestBody Detail detail) {
        Detail newDetail = detailService.saveDetail(detail);
        return ResponseEntity.ok(newDetail);
    }  
    

    /**
     * @param id unique detail identifier
     * @param updateDetail detail to be updated
     * @return returns the updated detail
     */    
    @Operation(summary = "Update detail", description = "Update detail")
    @PutMapping(path="/update/{id}")
    public ResponseEntity<Detail> updateDetail(@PathVariable Long id, @RequestBody Detail updateDetail) {
        Detail updatedDetail;
        try {
            updatedDetail = detailService.updateDetail(id, updateDetail);
            return ResponseEntity.ok(updatedDetail);
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
