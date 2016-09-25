package com.website.repository;

import com.website.model.CustomerPayment;
import com.website.model.Customer;
import lombok.Lombok;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * Created by tadasyan
 */
public interface CustomerPaymentRepository extends JpaRepository<CustomerPayment, Long> {

    public Page<CustomerPayment> findByCustomer(Customer customer, Pageable pageRequest);

}
