package com.newbeginnings.registry.service;

import com.newbeginnings.registry.model.Participant;
import com.newbeginnings.registry.repository.SimpleRegistryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

class RegistryServiceImplTest {

    @InjectMocks
    private RegistryServiceImpl service;

    @Mock
    private SimpleRegistryRepository repository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllParticipants() {
        Participant jane = new Participant("ABCD", "Jane", "Doe", LocalDate.of(1996, 4,23), 12345678, "The Cottage");
        Participant john = new Participant("DEFG", "John", "Doe", LocalDate.of(1996, 8,27), 87654321, "The Cottage");
        Collection<Participant> allParticipants = new ArrayList<>();
        allParticipants.add(jane);
        allParticipants.add(john);
        when(repository.retrieveAll()).thenReturn(allParticipants);

        Collection<Participant> retreivedParticipants = service.getAllParticipants();
        Assertions.assertIterableEquals(allParticipants, retreivedParticipants);
    }

    @Test
    void testGetParticipant() {
        Participant jane = new Participant("ABCD", "Jane", "Doe", LocalDate.of(1996, 4,23), 12345678, "The Cottage");
        when(repository.retrieve("ABCD")).thenReturn(jane);

        Participant result = service.getParticipant("ABCD");
        Assertions.assertEquals(jane, result);
    }

    @Test
    void testAddNewParticipant() {
        Participant jane = new Participant("ABCD", "Jane", "Doe", LocalDate.of(1996, 4,23), 12345678, "The Cottage");
        service.addParticipant(jane);

        Mockito.verify(repository, times(1)).add(jane);
    }

    @Test
    void testUpdateEntireParticipant() {
        Participant jane = new Participant("ABCD", "Jane", "Doe", LocalDate.of(1996, 4,23), 12345678, "The Cottage");
        Participant updatedJane = new Participant("ABCD", "Jane", "DoeDoe", LocalDate.of(1996, 4,23), 12345678, "The Cottage");
        when(repository.remove("ABCD")).thenReturn(jane);

        boolean isUpdated = service.updateParticipant(updatedJane);
        Mockito.verify(repository, times(1)).add(updatedJane);
        Assertions.assertTrue(isUpdated);
    }

    @Test
    void testUpdatePhoneNumber() {
        Participant jane = new Participant("ABCD", "Jane", "Doe", LocalDate.of(1996, 4,23), 12345678, "The Cottage");
        when(repository.remove("ABCD")).thenReturn(jane);

        boolean isUpdated = service.updateParticipant("ABCD", 32984793L, null);
        Assertions.assertTrue(isUpdated);
        Assertions.assertEquals("The Cottage", jane.getAddress());
        Assertions.assertEquals(32984793L, jane.getPhoneNumber());
    }

    @Test
    void testUpdateAddress() {
        Participant jane = new Participant("ABCD", "Jane", "Doe", LocalDate.of(1996, 4,23), 12345678, "The Cottage");
        when(repository.remove("ABCD")).thenReturn(jane);

        boolean isUpdated = service.updateParticipant("ABCD", null, "New Cottage");
        Assertions.assertTrue(isUpdated);
        Assertions.assertEquals("New Cottage", jane.getAddress());
        Assertions.assertEquals(12345678L, jane.getPhoneNumber());
    }

    @Test
    void testUpdatePhoneNumberAndAddress() {
        Participant jane = new Participant("ABCD", "Jane", "Doe", LocalDate.of(1996, 4,23), 12345678, "The Cottage");
        when(repository.remove("ABCD")).thenReturn(jane);

        boolean isUpdated = service.updateParticipant("ABCD", 32984793L, "New Cottage");
        Assertions.assertTrue(isUpdated);
        Assertions.assertEquals("New Cottage", jane.getAddress());
        Assertions.assertEquals(32984793L, jane.getPhoneNumber());
    }

    @Test
    void testRemoveParticipant() {
        Participant jane = new Participant("ABCD", "Jane", "Doe", LocalDate.of(1996, 4,23), 12345678, "The Cottage");
        when(repository.remove("ABCD")).thenReturn(jane);

        boolean isUpdated = service.removeParticipant("ABCD");
        Assertions.assertTrue(isUpdated);
    }



}