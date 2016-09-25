package com.website.service;

import com.website.model.Customer;
import com.website.model.CustomerPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by a.todosov.
 */
public interface CustomerService {

    void saveCustomerPayment(CustomerPayment customerPayment);

    Customer findByUsername(String username);

    Page<CustomerPayment> getPaymentsByUsername(String username, Pageable pageRequest);
}
