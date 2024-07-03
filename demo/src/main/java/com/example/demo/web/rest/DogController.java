package com.example.demo.web.rest;

import com.example.demo.model.DogBreed;
import com.example.demo.service.DogBreedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.DogService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DogController {

    private final DogBreedService dogBreedService;

    public DogController(DogBreedService dogService) {
        this.dogBreedService = dogService;
    }

    @GetMapping("/dogs")
    public List<DogBreed> fetch() {
        List<DogBreed> dogBreedList = dogBreedService.listAll();
        if(dogBreedList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return dogBreedList;
    }

    @GetMapping("/fetch")
    public ResponseEntity<String> fetchAndSaveDogs() {
        dogBreedService.saveDogBreedsFromApi();
        return ResponseEntity.ok("Dogs fetched and saved successfully");
    }

    @PostMapping("/dogs")
    @ResponseStatus(HttpStatus.CREATED)
    public DogBreed create(@RequestBody DogBreed dogBreed) {
        return dogBreedService.saveDogBreed(dogBreed);
    }

    @GetMapping("/dogs/{id}")
    public DogBreed getById(@PathVariable Long id) {
        return dogBreedService.getDogBreedById(id);
    }
}