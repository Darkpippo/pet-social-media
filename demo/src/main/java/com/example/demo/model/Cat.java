package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dog")
@Getter
@Setter
public class Cat extends Pet {

    private String catName;

    public Cat(User user, String catName) {
        super(user);
        this.catName = catName;
    }

    public Cat() {
    }
}
