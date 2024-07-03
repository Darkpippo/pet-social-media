package com.example.demo.service.impl;

import com.example.demo.model.exceptions.InvalidDogIdexception;
import com.fasterxml.jackson.core.type.TypeReference;
import com.example.demo.service.DogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.example.demo.model.Dog;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import com.example.demo.repository.DogRepository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DogServiceImpl implements DogService {
    private final DogRepository dogRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public DogServiceImpl(DogRepository dogRepository, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.dogRepository = dogRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public void saveDogsFromApi() {
        String apiUrl = "https://dogapi.dog/api/v2/breeds";
        String jsonData = restTemplate.getForObject(apiUrl, String.class);

        try {
            JsonNode dataNode = objectMapper.readTree(jsonData).get("data");

            if(dataNode.isArray()) {
                for (JsonNode node : dataNode) {
                    JsonNode attributes = node .get("attributes");

                    Dog dog = new Dog();

                    dog.setName(attributes.get("name").asText());
                    dog.setDescription(attributes.get("description").asText());
                    dog.setMaxLife(attributes.get("life").get("max").asInt());
                    dog.setMinLife(attributes.get("life").get("min").asInt());
                    dog.setMaxMaleWeight(attributes.get("male_weight").get("max").asInt());
                    dog.setMinMaleWeight(attributes.get("male_weight").get("min").asInt());
                    dog.setMaxFemaleWeight(attributes.get("female_weight").get("max").asInt());
                    dog.setMinFemaleWeight(attributes.get("female_weight").get("min").asInt());
                    dog.setHypoallergenic(attributes.get("hypoallergenic").asBoolean());

                    dogRepository.save(dog);
                }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Dog> listAll() {
        return dogRepository.findAll();
    }

    @Override
    public Dog saveDog(Dog dog) {
        return dogRepository.save(dog);
    }

    @Override
    public Dog getDogById(Long id) {
        return dogRepository.findById(id).orElseThrow(InvalidDogIdexception::new);
    }

    @Override
    public Dog updateDog(Long id, Dog updatedDog) {
        Dog existingDog = dogRepository.findById(id).orElseThrow(InvalidDogIdexception::new);

        existingDog.setName(updatedDog.getName());
        existingDog.setDescription(updatedDog.getDescription());
        existingDog.setMaxLife(updatedDog.getMaxLife());
        existingDog.setMinLife(updatedDog.getMinLife());
        existingDog.setMaxMaleWeight(updatedDog.getMaxMaleWeight());
        existingDog.setMinMaleWeight(updatedDog.getMinMaleWeight());
        existingDog.setMaxFemaleWeight(updatedDog.getMaxFemaleWeight());
        existingDog.setMinFemaleWeight(updatedDog.getMinFemaleWeight());
        existingDog.setHypoallergenic(updatedDog.isHypoallergenic());

        return dogRepository.save(existingDog);
    }
}
