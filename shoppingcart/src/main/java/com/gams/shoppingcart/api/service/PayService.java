package com.gams.shoppingcart.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.gams.shoppingcart.api.entity.Pay;
import com.gams.shoppingcart.api.repository.PayRepository;

@Service
public class PayService {
    @Autowired
    private PayRepository payRepository; 

    public Iterable<Pay> getAllPays(){ 
        return payRepository.findAll();
    } 

    public Optional<Pay> getPay(Long id){ 
        return payRepository.findById(id);
    }

    public Pay savePay(Pay purchaseOrder){ 
        return payRepository.save(purchaseOrder);
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
