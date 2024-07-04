package com.example.demo.service;

import com.example.demo.model.Pet;

import java.util.List;

public interface PetService {
    public Pet savePet(Pet pet);
    public Pet getPetById(Long id);
    public List<Pet> getAllPets();
    public void deletePetById(Long id);
}
