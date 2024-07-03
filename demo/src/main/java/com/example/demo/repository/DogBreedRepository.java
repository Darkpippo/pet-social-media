package com.example.demo.repository;

import com.example.demo.model.DogBreed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogBreedRepository extends JpaRepository<DogBreed, Long> {
}
