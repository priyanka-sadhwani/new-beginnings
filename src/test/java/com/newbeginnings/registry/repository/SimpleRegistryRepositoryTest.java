package com.newbeginnings.registry.repository;

import com.newbeginnings.registry.model.Participant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class SimpleRegistryRepositoryTest {

    @Test
    void testSimpleRegistryRepository() {
        SimpleRegistryRepository repo = new SimpleRegistryRepository();

        Participant jane = new Participant("ABCD", "Jane", "Doe", LocalDate.of(1996, 4,23), 12345678, "The Cottage");

        Assertions.assertEquals(0, repo.getRegister().size());
        repo.add(jane);
        Assertions.assertEquals(1, repo.getRegister().size());

        Participant retrievedJane = repo.retrieve("ABCD");
        Assertions.assertEquals(1, repo.getRegister().size());
        Assertions.assertEquals(jane, retrievedJane);

        repo.remove("ABCD");
        Assertions.assertEquals(0, repo.getRegister().size());
    }
}