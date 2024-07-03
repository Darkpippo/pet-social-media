package com.example.demo.service;

import com.example.demo.model.Dog;

import java.util.List;

public interface DogService {
    public void saveDogsFromApi();
    public List<Dog> listAll();
    public Dog saveDog(Dog dog);
    public Dog getDogById(Long id);
    public Dog updateDog(Long id, Dog updatedDog);
}
