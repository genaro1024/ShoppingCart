package com.gams.shoppingcart.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.gams.shoppingcart.api.entity.Customer;

public interface CustomerRespository extends CrudRepository<Customer,Long> {}
