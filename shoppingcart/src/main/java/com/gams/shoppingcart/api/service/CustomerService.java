package com.gams.shoppingcart.api.service;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import com.gams.shoppingcart.api.entity.Customer;
import com.gams.shoppingcart.api.repository.CustomerRespository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRespository customerRepository;

    public Iterable<Customer> getAllCustomers(){
        return customerRepository.findAll();
    } 

    public Optional<Customer> getCustomer(Long id){
        return customerRepository.findById(id);
    }

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer updateCustomer) throws NotFoundException {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setFirstName(updateCustomer.getFirstName());
            customer.setLastName(updateCustomer.getLastName());
            customer.setEmail(updateCustomer.getEmail());
            return customerRepository.save(customer);
        } else {
            throw new NotFoundException();
        }
    }


}
