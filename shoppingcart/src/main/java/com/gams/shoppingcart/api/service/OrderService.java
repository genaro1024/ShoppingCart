package com.gams.shoppingcart.api.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import com.gams.shoppingcart.api.entity.PurchaseOrder;
import com.gams.shoppingcart.api.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository purchaseOrderRepository; 

    public Iterable<PurchaseOrder> getAllPurchaseOrders(){ 
        return purchaseOrderRepository.findAll();
    } 

    public Optional<PurchaseOrder> getPurchaseOrder(Long id){ 
        return purchaseOrderRepository.findById(id);
    }

    public PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder){ 
        return purchaseOrderRepository.save(purchaseOrder);
    }

    public PurchaseOrder updatePurchaseOrder(Long id, PurchaseOrder updatePurchaseOrder) throws NotFoundException {
        Optional<PurchaseOrder> optionalPurchaseOrder = purchaseOrderRepository.findById(id);
        if (optionalPurchaseOrder.isPresent()) {
            PurchaseOrder purchaseOrder = optionalPurchaseOrder.get();
            purchaseOrder.setDate(updatePurchaseOrder.getDate());
            purchaseOrder.setCustomer(updatePurchaseOrder.getCustomer());
            purchaseOrder.setDetails(updatePurchaseOrder.getDetails());
            purchaseOrder.setPay(updatePurchaseOrder.getPay());
            return purchaseOrderRepository.save(purchaseOrder);
        } else {
            throw new NotFoundException();
        }
    }

}
