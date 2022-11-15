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
        Pet expected = new Pet();
        expected.setId(1L);
        expected.setName("Ricardo");
        expected.setTag("Cat");
        expected.setAge(23L);

        return new ResponseEntity<>(expected, HttpStatus.OK);
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
        pet1.setAge(25L);

        Pet pet2 = new Pet();
        pet2.setId(1L);
        pet2.setName("Ricardo");
        pet2.setTag("Cat");
        pet2.setAge(23L);

        List<Pet> expected = new ArrayList<>();
        expected.add(pet1);
        expected.add(pet2);
        return new ResponseEntity<>(expected, HttpStatus.OK);
    }
}
