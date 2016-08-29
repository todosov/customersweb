package com.website.service;

import com.website.dao.CustomerDAO;
import com.website.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by a.todosov.
 */
@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO dao;

    @Override
    public List<Customer> findAllCustomers() {
        return dao.findAllCustomers();
    }

    @Override
    public void saveCustomer(Customer customer) {
        dao.saveCustomer(customer);
    }

    @Override
    public Customer findByUsername(String username) {
        return dao.findByUsername(username);
    }
}
