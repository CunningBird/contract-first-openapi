package com.cunningbird.contractfirst.openapi.contract;

import com.cunningbird.contractfirst.openapi.contract.model.Pet;
import com.cunningbird.contractfirst.openapi.contract.mock.Application;
import com.cunningbird.contractfirst.openapi.contract.mock.PetsClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = Application.class,
        properties = { "api.address=http://localhost:8080", "server.port=8080" }
)
@AutoConfigureMockMvc
public class CapabilityTest {

    @Autowired
    private PetsClient client;

    @Test
    public void testListPets() {
        ResponseEntity<List<Pet>> response = client.listPets(2);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

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

        List<Pet> actual = response.getBody();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testShowPetById() {
        ResponseEntity<Pet> response = client.showPetById(1L);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Pet expected = new Pet();
        expected.setId(1L);
        expected.setName("Ricardo");
        expected.setTag("Cat");
        expected.setAge(23L);


        Pet actual = response.getBody();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testCreatePet() {
        Pet pet = new Pet();
        pet.setId(4L);
        pet.setName("Billy");
        pet.setTag("cat");
        pet.setAge(23L);
        ResponseEntity<Void> response = client.createPet(pet);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
