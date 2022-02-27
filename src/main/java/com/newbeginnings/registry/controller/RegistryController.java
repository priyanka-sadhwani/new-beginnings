package com.newbeginnings.registry.controller;

import com.newbeginnings.registry.model.Participant;
import com.newbeginnings.registry.service.RegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class RegistryController {

    @Autowired
    RegistryService registryService;

    @RequestMapping(value = "/participants", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllParticipants() {
        Collection<Participant> participants = registryService.getAllParticipants();
        if (!participants.isEmpty()) return new ResponseEntity<>(participants, HttpStatus.OK);
        else return new ResponseEntity<>("No participants found", HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/participants/{refNumber}", method = RequestMethod.GET)
    public ResponseEntity<Object> getParticipant(@PathVariable("refNumber") String refNumber) {
        Participant participant = registryService.getParticipant(refNumber);
        if (participant != null) return new ResponseEntity<>(participant, HttpStatus.OK);
        else return new ResponseEntity<>("Participant not found", HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/participants", method = RequestMethod.POST)
    public ResponseEntity<Object> addParticipant(@RequestBody Participant participant) {
        registryService.addParticipant(participant);
        return new ResponseEntity<>("Participant is successfully added", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/participants", method = RequestMethod.PUT)
    //remove refNumber from path variable?
    public ResponseEntity<Object> updateParticipant(@RequestBody Participant participant) {
        boolean isRegisterUpdated = registryService.updateParticipant(participant);
        if (isRegisterUpdated) return new ResponseEntity<>("Participant is successfully updated", HttpStatus.OK);
        else return new ResponseEntity<>("Participant not found", HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/participants/{refNumber}", method = RequestMethod.PATCH)
    public ResponseEntity<Object> partiallyUpdateParticipant(@PathVariable("refNumber") String refNumber,
                                                             @RequestParam(required=false, name = "phoneNumber") Long phoneNumber,
                                                             @RequestParam(required=false, name = "address") String address) {
        if (phoneNumber == null && address == null) return new ResponseEntity<>("Please add the updated phoneNumber and/or address as a query parameter", HttpStatus.BAD_REQUEST);
        boolean isRegisterUpdated = registryService.updateParticipant(refNumber, phoneNumber, address);
        if (isRegisterUpdated) return new ResponseEntity<>("Participant is successfully updated", HttpStatus.OK);
        else return new ResponseEntity<>("Participant not found", HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/participants/{refNumber}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> removeParticipant(@PathVariable("refNumber") String refNumber) {
        boolean isRegisterUpdated = registryService.removeParticipant(refNumber);
        if (isRegisterUpdated) return new ResponseEntity<>("Participant is successfully removed", HttpStatus.OK);
        else return new ResponseEntity<>("Participant not found", HttpStatus.NO_CONTENT);
    }

}
