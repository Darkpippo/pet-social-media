package com.example.demo.service;

import com.example.demo.model.Dog;
import com.example.demo.model.DogBreed;

import java.util.List;

public interface DogBreedService {
    public void saveDogBreedsFromApi();
    public List<DogBreed> listAll();
    public DogBreed saveDogBreed(DogBreed dogBreed);
    public DogBreed getDogBreedById(Long id);
    public DogBreed updateDogBreed(Long id, DogBreed updatedDogBreed);
}
