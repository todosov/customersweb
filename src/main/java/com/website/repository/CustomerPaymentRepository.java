package com.website.repository;

import com.website.model.CustomerPayment;
import lombok.Lombok;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by tadasyan
 */
public interface CustomerPaymentRepository extends JpaRepository<CustomerPayment, Long> {
}
