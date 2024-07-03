package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "dog")
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(name = "description", length = 1500)
    private String description;
    private Integer maxLife;
    private Integer minLife;
    private Integer maxMaleWeight;
    private Integer minMaleWeight;
    private Integer maxFemaleWeight;
    private Integer minFemaleWeight;
    private boolean hypoallergenic;

    public Dog(Long id, String name, String description, Integer maxLife, Integer minLife, Integer maxMaleWeight, Integer minMaleWeight, Integer maxFemaleWeight, Integer minFemaleWeight, boolean hypoallergenic) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.maxLife = maxLife;
        this.minLife = minLife;
        this.maxMaleWeight = maxMaleWeight;
        this.minMaleWeight = minMaleWeight;
        this.maxFemaleWeight = maxFemaleWeight;
        this.minFemaleWeight = minFemaleWeight;
        this.hypoallergenic = hypoallergenic;
    }

    public Dog() {
    }


}
