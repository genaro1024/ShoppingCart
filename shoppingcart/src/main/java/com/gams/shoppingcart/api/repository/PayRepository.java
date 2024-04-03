package com.gams.shoppingcart.api.repository;

import org.springframework.data.repository.CrudRepository;
import com.gams.shoppingcart.api.entity.Pay;

public interface PayRepository extends CrudRepository<Pay,Long> {

}
