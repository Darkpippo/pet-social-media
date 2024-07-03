package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dog_breed")
@Getter
@Setter
public class DogBreed extends Dog {

    private String breedName;

    @Column(name = "description", length = 1500)
    private String description;

    private Integer maxLife;
    private Integer minLife;
    private Integer maxMaleWeight;
    private Integer minMaleWeight;
    private Integer maxFemaleWeight;
    private Integer minFemaleWeight;
    private boolean hypoallergenic;

    public DogBreed(User user, String dogName, String breedName, String description, Integer maxLife, Integer minLife, Integer maxMaleWeight, Integer minMaleWeight, Integer maxFemaleWeight, Integer minFemaleWeight, boolean hypoallergenic) {
        super(user, dogName);
        this.breedName = breedName;
        this.description = description;
        this.maxLife = maxLife;
        this.minLife = minLife;
        this.maxMaleWeight = maxMaleWeight;
        this.minMaleWeight = minMaleWeight;
        this.maxFemaleWeight = maxFemaleWeight;
        this.minFemaleWeight = minFemaleWeight;
        this.hypoallergenic = hypoallergenic;
    }

    public DogBreed() {
    }
}
