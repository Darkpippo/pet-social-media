package com.example.demo.rest;

import com.example.demo.model.Dog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.DogService;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DogController {

    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping("/dogs")
    public List<Dog> fetch() {
        List<Dog> dogList = dogService.listAll();
        if(dogList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return dogList;
    }

    @GetMapping("/fetch")
    public ResponseEntity<String> fetchAndSaveDogs() {
        dogService.saveDogsFromApi();
        return ResponseEntity.ok("Dogs fetched and saved successfully");
    }

    @PostMapping("/dogs")
    @ResponseStatus(HttpStatus.CREATED)
    public Dog create(@RequestBody Dog dog) {
        return dogService.saveDog(dog);
    }

    @GetMapping("/dogs/{id}")
    public Dog getById(@PathVariable Long id) {
        return dogService.getDogById(id);
    }
}