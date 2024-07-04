package com.example.demo.service.impl;

import com.example.demo.model.Pet;
import com.example.demo.model.exceptions.InvalidDogIdexception;
import com.example.demo.repository.PetRepository;
import com.example.demo.service.PetService;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Pet getPetById(Long id) {
        return petRepository.findById(id).orElseThrow(InvalidDogIdexception::new);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    @Override
    public void deletePetById(Long id) {
        Pet pet = getPetById(id);
        petRepository.delete(pet);
    }
}
