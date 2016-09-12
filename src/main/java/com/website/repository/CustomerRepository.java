package com.website.repository;

import com.website.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

/**
 * Created by tadasyan
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByUsername(String username);
}
