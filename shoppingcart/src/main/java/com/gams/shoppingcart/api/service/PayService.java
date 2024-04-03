package com.gams.shoppingcart.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.gams.shoppingcart.api.entity.Detail;
import com.gams.shoppingcart.api.entity.Pay;
import com.gams.shoppingcart.api.entity.Product;
import com.gams.shoppingcart.api.entity.PurchaseOrder;
import com.gams.shoppingcart.api.repository.DetailRepository;
import com.gams.shoppingcart.api.repository.OrderRepository;
import com.gams.shoppingcart.api.repository.PayRepository;

@Service
public class PayService {
    @Autowired
    private PayRepository payRepository;
    @Autowired
    private OrderRepository orderRepository; 
    @Autowired
    private DetailRepository detailRepository;      
    @Autowired
    private ProductServiceClient productServiceClient;    

    public Iterable<Pay> getAllPays(){ 
        return payRepository.findAll();
    } 

    public Optional<Pay> getPay(Long id){ 
        return payRepository.findById(id);
    }

    public Pay savePay(Pay pay){ 
        long orderId = pay.getOrder().getId();
        PurchaseOrder order = orderRepository.findById(orderId).get();
        Iterable<Detail> details = detailRepository.findByOrderId(order.getId());
        float amount = 0.0f;
        for (Detail detail : details) {
            long product_id = detail.getProductId();
            Product product = productServiceClient.getProductById(product_id).block();
            float price = product.getPrice();
            amount+=price*detail.getQuantity();
        }
        pay.setAmount(amount);
        return payRepository.save(pay);
    }

    public Pay updatePay(Long id, Pay updatePay) throws NotFoundException {
        Optional<Pay> optionalPay = payRepository.findById(id);
        if (optionalPay.isPresent()) {
            Pay pay = optionalPay.get();
            pay.setAmount(updatePay.getAmount());
            pay.setDate(updatePay.getDate());
            pay.setOrder(updatePay.getOrder());
            pay.setPaid(updatePay.isPaid());
            pay.setVoucher(updatePay.getVoucher());
            return payRepository.save(pay);
        } else {
            throw new NotFoundException();
        }
    }

}
