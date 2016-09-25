package com.website.service;

import com.website.model.Customer;
import com.website.model.CustomerPayment;
import com.website.repository.CustomerPaymentRepository;
import com.website.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by a.todosov.
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerPaymentRepository customerPaymentRepository;

    @Override
    public void saveCustomerPayment(CustomerPayment customerPayment) {
        customerPaymentRepository.save(customerPayment);
        Customer customer = customerRepository.findOne(customerPayment.getCustomer().getId());
        Set<CustomerPayment> payments = customer.getCustomerPayments();
        customerRepository.updateTotalAmount(payments.stream().mapToDouble(p -> p.getAmount()).sum(), customer.getId());
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findCustomerByUsername(username);
    }

    @Override
    public Page<CustomerPayment> getPaymentsByUsername(String username, Pageable pageRequest) {
        return customerPaymentRepository.findByCustomer(customerRepository.findCustomerByUsername(username), pageRequest);
    }
}
