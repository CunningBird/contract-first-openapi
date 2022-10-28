package com.cunningbird.templates.contractfirstrest.controller;

import com.cunningbird.templates.contractfirstrest.api.PetsApi;
import com.cunningbird.templates.contractfirstrest.model.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

public class PetsApiMock implements PetsApi {

    @Override
    public ResponseEntity<List<Pet>> listPets(Integer limit) {
        Pet pet1 = new Pet();
        pet1.setId(1L);
        pet1.setName("Ricardo");
        pet1.setTag("Cat");
        pet1.setAge("1");

        Pet pet2 = new Pet();
        pet2.setId(1L);
        pet2.setName("Ricardo");
        pet2.setTag("Cat");
        pet2.setName("2");

        List<Pet> pets = new ArrayList<>();
        pets.add(pet1);
        pets.add(pet2);
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Pet> showPetById(Long petId) {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Ricardo");
        pet.setTag("Cat");
        pet.setName("1");

        return new ResponseEntity<>(pet, HttpStatus.OK);
    }
}
