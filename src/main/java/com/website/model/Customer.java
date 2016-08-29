package com.website.model;

/**
 * Created by tadasyan on 18.07.16.
 */

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

//    @Column(name = "total_amount", nullable = false, length = 20)
//    private int totalAmount;

    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @OneToMany(mappedBy = "customer")
    private Set<CustomerPayment> customerPayments;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "customer_profile", joinColumns = {@JoinColumn(name = "customer_id")}, inverseJoinColumns = {@JoinColumn(name = "profile_id")})
    private Set<Profile> profiles;
}
