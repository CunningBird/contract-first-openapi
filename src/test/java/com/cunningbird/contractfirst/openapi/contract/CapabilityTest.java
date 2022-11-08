package com.cunningbird.contractfirst.openapi.contract;

import com.cunningbird.contractfirst.openapi.contract.api.PetsApi;
import com.cunningbird.contractfirst.openapi.contract.mock.PetsApiMock;
import com.cunningbird.contractfirst.openapi.contract.model.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

public class CapabilityTest {

    private final PetsApi server = new PetsApiMock();

    @Test
    public void testShowPetById() {
        ResponseEntity<Pet> response = server.showPetById(1L);

        Pet pet = response.getBody();
        Assertions.assertNotNull(pet);
        Assertions.assertEquals(pet.getId(), 1L);
        Assertions.assertEquals(pet.getName(), "Ricardo");
        Assertions.assertEquals(pet.getTag(), "Cat");
    }
}
