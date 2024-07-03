package com.example.demo.service.impl;

import com.example.demo.model.Dog;
import com.example.demo.model.exceptions.InvalidDogIdexception;
import com.example.demo.repository.DogBreedRepository;
import com.example.demo.service.DogBreedService;
import com.example.demo.service.DogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.example.demo.model.DogBreed;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import com.example.demo.repository.DogRepository;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class DogServiceImpl implements DogBreedService {
    private final DogBreedRepository dogBreedRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public DogServiceImpl(DogBreedRepository dogBreedRepository, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.dogBreedRepository = dogBreedRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public void saveDogBreedsFromApi() {
        String apiUrl = "https://dogapi.dog/api/v2/breeds";
        String jsonData = restTemplate.getForObject(apiUrl, String.class);

        try {
            JsonNode dataNode = objectMapper.readTree(jsonData).get("data");

            if(dataNode.isArray()) {
                for (JsonNode node : dataNode) {
                    JsonNode attributes = node .get("attributes");

                    DogBreed dogBreed = new DogBreed();

                    dogBreed.setBreedName(attributes.get("name").asText());
                    dogBreed.setDescription(attributes.get("description").asText());
                    dogBreed.setMaxLife(attributes.get("life").get("max").asInt());
                    dogBreed.setMinLife(attributes.get("life").get("min").asInt());
                    dogBreed.setMaxMaleWeight(attributes.get("male_weight").get("max").asInt());
                    dogBreed.setMinMaleWeight(attributes.get("male_weight").get("min").asInt());
                    dogBreed.setMaxFemaleWeight(attributes.get("female_weight").get("max").asInt());
                    dogBreed.setMinFemaleWeight(attributes.get("female_weight").get("min").asInt());
                    dogBreed.setHypoallergenic(attributes.get("hypoallergenic").asBoolean());

                    dogBreedRepository.save(dogBreed);
                }
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DogBreed> listAll() {
        return dogBreedRepository.findAll();
    }

    @Override
    public DogBreed saveDogBreed(DogBreed dogBreed) {
        return dogBreedRepository.save(dogBreed);
    }

    @Override
    public DogBreed getDogBreedById(Long id) {
        return dogBreedRepository.findById(id).orElseThrow(InvalidDogIdexception::new);
    }

    @Override
    public DogBreed updateDogBreed(Long id, DogBreed updatedDogBreed); {
        DogBreed existingDogBreed = dogBreedRepository.findById(id).orElseThrow(InvalidDogIdexception::new);

        existingDogBreed.setBreedName(updatedDogBreed.getBreedName());
        existingDogBreed.setDescription(updatedDogBreed.getDescription());
        existingDogBreed.setMaxLife(updatedDogBreed.getMaxLife());
        existingDogBreed.setMinLife(updatedDogBreed.getMinLife());
        existingDogBreed.setMaxMaleWeight(updatedDogBreed.getMaxMaleWeight());
        existingDogBreed.setMinMaleWeight(updatedDogBreed.getMinMaleWeight());
        existingDogBreed.setMaxFemaleWeight(updatedDogBreed.getMaxFemaleWeight());
        existingDogBreed.setMinFemaleWeight(updatedDogBreed.getMinFemaleWeight());
        existingDogBreed.setHypoallergenic(updatedDogBreed.isHypoallergenic());

        return dogBreedRepository.save(existingDogBreed);
    }
}
