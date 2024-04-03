package com.gams.shoppingcart.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.gams.shoppingcart.api.entity.Detail;

public interface DetailRepository extends CrudRepository<Detail,Long> {
    Iterable<Detail> findByOrderId(Long orderId);
}
