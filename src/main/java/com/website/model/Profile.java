package com.website.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by a.todosov.
 */

@Data
@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    private String type;

}
