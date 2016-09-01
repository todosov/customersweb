package com.website.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by tadasyan on 18.07.16.
 */

@Data
@Entity
@Table(name = "customer_payment")
public class CustomerPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}
