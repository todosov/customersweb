package com.website.service;

import com.website.model.Customer;
import com.website.model.CustomerPayment;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by a.todosov.
 */
public interface CustomerService {

    List<Customer> findAllCustomers();

    void saveCustomer(Customer customer);

    void saveCustomerPayment(CustomerPayment customerPayment);

    Customer findByUsername(String username);
}
