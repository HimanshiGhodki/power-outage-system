package com.cde.power_supply_customer_registration.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer registration_no;

    @Column(name = "customer_name")
    @NotNull(message = "Customer name should not be null")
    private String customer_name;

    @Column(name = "username", unique = true)
    @NotNull(message = "User name should not be null")
    private String username;

    @Column(name = "password")
    @NotNull(message = "Password should not be null")
    private String password;

    @Column(name = "roles")
    private String roles;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "address")
    @NotNull(message = "Address should not be null")
    private String address;

    @Column(name = "state")
    @NotNull(message = "State should not be null")
    private String state;

    @Column(name = "country")
    @NotNull(message = "Country should not be null")
    private String country;

    @Column(name = "zip_code")
    @NotNull(message = "Zip code should not be null")
    private String zip_code;

    @Column(name = "email")
    @NotNull(message = "Email should not be null")
    private String email;

    @Column(name = "PAN_no")
    @NotNull(message = "PAN number should not be null")
    private String PAN_no;

    @Column(name = "contact_no")
    @NotNull(message = "Contact number should not be null")
    private Long contact_no;

    @Past(message = "Date of birth should be in the past")
    @Column(name = "date_of_birth")
    @NotNull(message = "Date of birth should not be null")
    private Date date_of_birth;

    @Column(name = "account")
    @NotNull(message = "Account should not be null")
    private String account;

    public Customer(Integer registration_no, @NotNull(message = "Customer name should not be null") String customer_name, @NotNull(message = "User name should not be null") String username, String password, String roles, Boolean active, @NotNull(message = "Address should not be null") String address, @NotNull(message = "State should not be null") String state, @NotNull(message = "Country should not be null") String country, @NotNull(message = "Zip code should not be null") @Size(min = 10, max = 10, message = "phone number should be of 10 digits") String zip_code, @NotNull(message = "Email should not be null") String email, @NotNull(message = "PAN number should not be null") String PAN_no, @NotNull(message = "Contact number should not be null") @Size(min = 10, max = 10, message = "phone number should be of 10 digits") Long contact_no, @Past(message = "Date of birth should be in the past") @NotNull(message = "Date of birth should not be null") Date date_of_birth, @NotNull(message = "Account should not be null") String account) {
        this.registration_no = registration_no;
        this.customer_name = customer_name;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.active = active;
        this.address = address;
        this.state = state;
        this.country = country;
        this.zip_code = zip_code;
        this.email = email;
        this.PAN_no = PAN_no;
        this.contact_no = contact_no;
        this.date_of_birth = date_of_birth;
        this.account = account;
    }

    public Customer() {
    }

}
