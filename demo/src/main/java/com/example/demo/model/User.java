package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "table_users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;


}
