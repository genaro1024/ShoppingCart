package com.gams.shoppingcart.api.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import com.gams.shoppingcart.api.entity.Detail;
import com.gams.shoppingcart.api.repository.DetailRepository;

@Service
public class DetailService {
    @Autowired
    private DetailRepository detailRepository; 

    public Iterable<Detail> getAllDetails(){ 
        return detailRepository.findAll();
    } 

    public Iterable<Detail> getAllDetailsByOrderId(Long id){ 
        return detailRepository.findByOrderId(id);
    }     

    public Optional<Detail> getDetail(Long id){ 
        return detailRepository.findById(id);
    }

    public Detail saveDetail(Detail purchaseOrder){ 
        return detailRepository.save(purchaseOrder);
    }

    public Detail updateDetail(Long id, Detail updateDetail) throws NotFoundException {
        Optional<Detail> optionalDetail = detailRepository.findById(id);
        if (optionalDetail.isPresent()) {
            Detail detail = optionalDetail.get();
            detail.setOrder(updateDetail.getOrder());
            detail.setProductId(updateDetail.getProductId());
            detail.setQuantity(updateDetail.getQuantity());
            return detailRepository.save(detail);
        } else {
            throw new NotFoundException();
        }
    }


}
