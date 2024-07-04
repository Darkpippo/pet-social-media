package com.example.demo.web.rest;

import com.example.demo.model.Dog;
import com.example.demo.model.Pet;
import com.example.demo.repository.DogRepository;
import com.example.demo.service.DogBreedService;
import com.example.demo.service.DogService;
import com.example.demo.service.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
public class DogController {
    private final DogService dogService;
    private final PetService petService;

    public DogController(DogService dogService, PetService petService) {
        this.dogService = dogService;
        this.petService = petService;
    }

    @PostMapping("/dogs")
    public ResponseEntity<Dog> createDog(@RequestBody Dog dog) {
        return new ResponseEntity<Dog>(dogService.save(dog), HttpStatus.CREATED);
    }

    @PostMapping("/pets")
    public ResponseEntity<Pet> createPet(@RequestBody Pet pet) {
        return new ResponseEntity<Pet>(petService.savePet(pet), HttpStatus.CREATED);
    }
}
