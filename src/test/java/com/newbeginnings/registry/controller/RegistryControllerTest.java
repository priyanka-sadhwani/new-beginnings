package com.newbeginnings.registry.controller;

import com.newbeginnings.registry.model.Participant;
import com.newbeginnings.registry.service.RegistryServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(RegistryController.class)
class RegistryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegistryServiceImpl service;

    @Test
    void testGetAllParticipants() throws Exception {
        Participant jane = new Participant("ABCD", "Jane", "Doe", LocalDate.of(1996, 4,23), 12345678, "The Cottage");
        Participant john = new Participant("DEFG", "John", "Doe", LocalDate.of(1996, 8,27), 87654321, "The Cottage");
        Collection<Participant> allParticipants = new ArrayList<>();
        allParticipants.add(jane);
        allParticipants.add(john);
        when(service.getAllParticipants()).thenReturn(allParticipants);

        Path expectedResults = Paths.get("src/test/resources/allparticipants.json");

        this.mockMvc.perform(get("/participants")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(Files.readString(expectedResults)));
    }

    @Test
    void testGetAllParticipantsReturnsNoContent() throws Exception {
        when(service.getAllParticipants()).thenReturn(Collections.emptyList());

        this.mockMvc.perform(get("/participants")).andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().string("No participants found"));
    }

    @Test
    void testGetParticipant() throws Exception {
        Participant jane = new Participant("ABCD", "Jane", "Doe", LocalDate.of(1996, 4,23), 12345678, "The Cottage");
        when(service.getParticipant("ABCD")).thenReturn(jane);

        Path expectedResults = Paths.get("src/test/resources/jane.json");

        this.mockMvc.perform(get("/participants/ABCD")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(Files.readString(expectedResults)));
    }

    @Test
    void testAddParticipant() throws Exception {
        Participant jane = new Participant("ABCD", "Jane", "Doe", LocalDate.of(1996, 4,23), 12345678, "The Cottage");
        Path expectedResults = Paths.get("src/test/resources/jane.json");

        this.mockMvc.perform(post("/participants")
                .contentType("application/json").content(Files.readString(expectedResults))).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("Participant is successfully added"));

        Mockito.verify(service, times(1)).addParticipant(jane);
    }

    @Test
    void testUpdateParticipant() {
    }

    @Test
    void testPartiallyUpdateParticipantOnlyPhoneNumber() {
    }

    @Test
    void testPartiallyUpdateParticipantOnlyAddress() {
    }

    @Test
    void testPartiallyUpdateParticipantPhoneNumberAndAddress() {
    }

    @Test
    void testRemoveParticipant() {
    }
}