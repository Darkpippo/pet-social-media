package com.example.demo.service.impl;

import com.example.demo.model.Dog;
import com.example.demo.model.exceptions.InvalidDogIdexception;
import com.example.demo.repository.DogRepository;
import com.example.demo.service.DogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogServiceImpl implements DogService {
    private static final Logger log = LoggerFactory.getLogger(DogServiceImpl.class);
    private final DogRepository dogRepository;

    public DogServiceImpl(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public List<Dog> listAll() {
        return dogRepository.findAll();
    }

    @Override
    public Dog save(Dog dog) {
        return dogRepository.save(dog);
    }

    @Override
    public Dog findById(Long id) {
        return dogRepository.findById(id).orElseThrow(InvalidDogIdexception::new);
    }

    @Override
    public void deleteById(Long id) {
        Dog dog = findById(id);
        dogRepository.delete(dog);
        log.info("Dog successfully deleted");
    }
}
