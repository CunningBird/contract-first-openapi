package com.cunningbird.contractfirst.openapi.contract.mock;

import com.cunningbird.contractfirst.openapi.contract.api.PetsApi;
import com.cunningbird.contractfirst.openapi.contract.model.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PetsApiImpl implements PetsApi {

    @Override
    public ResponseEntity<Pet> showPetById(Long petId) {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Ricardo");
        pet.setTag("Cat");

        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> createPet(Pet pet) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Pet>> listPets(Integer limit) {
        Pet pet1 = new Pet();
        pet1.setId(1L);
        pet1.setName("Ricardo");
        pet1.setTag("Cat");

        Pet pet2 = new Pet();
        pet2.setId(1L);
        pet2.setName("Ricardo");
        pet2.setTag("Cat");

        List<Pet> pets = new ArrayList<>();
        pets.add(pet1);
        pets.add(pet2);
        return new ResponseEntity<>(pets, HttpStatus.OK);
    }
}
