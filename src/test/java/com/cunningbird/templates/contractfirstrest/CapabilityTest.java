package com.cunningbird.templates.contractfirstrest;

import com.cunningbird.templates.contractfirstrest.api.PetsApi;
import com.cunningbird.templates.contractfirstrest.controller.PetsApiMock;
import com.cunningbird.templates.contractfirstrest.model.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

public class CapabilityTest {

    private final PetsApi server = new PetsApiMock();

    @Test
    public void test1() {
        ResponseEntity<Pet> response = server.showPetById(1L);

        Pet pet = response.getBody();
        Assertions.assertNotNull(pet);
        Assertions.assertEquals(pet.getId(), 1L);
        Assertions.assertEquals(pet.getName(), "Ricardo");
        Assertions.assertEquals(pet.getTag(), "Cat");
    }
}
