package com.example.demo.config;

import com.example.demo.model.Dog;
import com.example.demo.model.DogBreed;
import com.example.demo.model.User;
import com.example.demo.repository.DogBreedRepository;
import com.example.demo.repository.DogRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private DogBreedRepository dogBreedRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User("john_doe", "password123", "John", "Doe", "john.doe@example.com", "123-456-7890");
        user.setAddress("123 Elm Street");
        user.setCity("Springfield");
        userRepository.save(user);

        Dog dog = new Dog(user, "Buddy");
        dogRepository.save(dog);

        DogBreed dogBreed = new DogBreed(user, dog.getDogName(),"Golden Retriever", "A friendly and intelligent breed.", 15, 10, 40, 30, 35, 25, true);
        dogBreedRepository.save(dogBreed);

        System.out.println("Inserted User ID: " + user.getId());
        System.out.println("Inserted DogBreed ID: " + dogBreed.getId());
        System.out.println("Inserted Dog ID: " + dog.getId());
    }
}
