package com.example.demo.model;


import com.example.demo.model.exceptions.InvalidCredentialsException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;

    @ManyToMany
    private List<Pet> petList;

    public User() {

    }

    public User(String username, String password, String firstName, String lastName, String email, String phone) {
        if (email == null && phone == null) {
            throw new InvalidCredentialsException();
        }
        if (email != null && email.isEmpty()) {
            throw new InvalidCredentialsException();
        }
        if (phone != null && phone.isEmpty()) {
            throw new InvalidCredentialsException();
        }
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;  // Make sure to initialize all required fields
        this.phone = phone;
    }
}
