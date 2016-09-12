package com.website.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by tadasyan on 18.07.16.
 */

@Getter
@Setter
@Entity
@Table(name = "customer_payment")
public class CustomerPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private     Customer customer;

    public String getFormattedDate(DateTimeFormatter formatter){
        return date.format(formatter);
    }

    public String getFormattedDate(){
        return date.format(DateTimeFormatter.ofPattern("dd-MMM-yyy"));
    }
}
