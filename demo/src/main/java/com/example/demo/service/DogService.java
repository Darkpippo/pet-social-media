package com.example.demo.service;

import com.example.demo.model.Dog;

import java.util.List;

public interface DogService {

    public List<Dog> listAll();
    public Dog save(Dog dog);
    public Dog findById(Long id);
    public void deleteById(Long id);
}
