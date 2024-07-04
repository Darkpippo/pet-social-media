package com.example.demo.web.rest;

import com.example.demo.model.DogBreed;
import com.example.demo.service.DogBreedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DogBreedController {

    private final DogBreedService dogBreedService;

    public DogBreedController(DogBreedService dogService) {
        this.dogBreedService = dogService;
    }

    @GetMapping("/breeds")
    public List<DogBreed> fetch() {
        List<DogBreed> dogBreedList = dogBreedService.listAll();
        if(dogBreedList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return dogBreedList;
    }

    @GetMapping("/breed-fetch")
    public ResponseEntity<String> fetchAndSaveDogs() {
        dogBreedService.saveDogBreedsFromApi();
        return ResponseEntity.ok("Dogs fetched and saved successfully");
    }

    @PostMapping("/dog-breed")
    @ResponseStatus(HttpStatus.CREATED)
    public DogBreed saveDogBreed(@RequestBody DogBreed dogBreed) {
        return dogBreedService.saveDogBreed(dogBreed);
    }

    @GetMapping("/dog-breed/{id}")
    public DogBreed getDogBreedById(@PathVariable Long id) {
        return dogBreedService.getDogBreedById(id);
    }
}