package com.example.zuulgateway.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "customers")
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_no")
    private Integer registration_no;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    private String roles;

    @Column(name = "active")
    private Boolean active;
}
