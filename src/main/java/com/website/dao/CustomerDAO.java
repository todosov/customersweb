package com.website.dao;

import com.website.model.Customer;

import java.util.List;

/**
 * Created by tadasyan on 18.07.16.
 */
public interface CustomerDAO {

    List<Customer> findAllCustomers();

    void saveCustomer(Customer customer);

    Customer findByUsername(String username);

}
