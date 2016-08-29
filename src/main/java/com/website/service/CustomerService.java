package com.website.service;

import com.website.dao.CustomerDAO;
import com.website.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by a.todosov.
 */
public interface CustomerService {

    List<Customer> findAllCustomers();

    void saveCustomer(Customer customer);

    Customer findByUsername(String username);
}
