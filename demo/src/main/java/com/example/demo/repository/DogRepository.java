package com.example.demo.repository;

import com.example.demo.model.Dog;
import com.example.demo.model.DogBreed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository  extends JpaRepository<Dog, Long> {
}
