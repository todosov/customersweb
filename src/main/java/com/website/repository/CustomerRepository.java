package com.website.repository;

import com.website.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * Created by tadasyan
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByUsername(String username);

    @Modifying
    @Transactional
    @Query("update Customer c set c.totalAmount = ?1 where c.id = ?2")
    void updateTotalAmount(Double totalAmount, Long id);

}
