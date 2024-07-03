package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dog")
@Getter
@Setter
public class Dog extends Pet {

    private String dogName;

    public Dog(User user, String dogName) {
        super(user);
        this.dogName = dogName;
    }

    public Dog() {
    }
}
