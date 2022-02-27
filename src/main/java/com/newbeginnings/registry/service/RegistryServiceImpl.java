package com.newbeginnings.registry.service;

import com.newbeginnings.registry.model.Participant;
import com.newbeginnings.registry.repository.RegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RegistryServiceImpl implements RegistryService {

    @Autowired
    private RegistryRepository registryRepository;

    @Override
    public Collection<Participant> getAllParticipants() {
        return registryRepository.retrieveAll();
    }

    @Override
    public Participant getParticipant(String refNumber) {
        return registryRepository.retrieve(refNumber);
    }

    @Override
    public void addParticipant(Participant participant) {
        registryRepository.add(participant);
    }

    @Override
    public boolean updateParticipant(Participant participant) {
        String referenceNumber = participant.getReferenceNumber();
        Participant existingParticipant = registryRepository.remove(referenceNumber);
        if (existingParticipant == null) return false;
        else {
            registryRepository.add(participant);
            return true;
        }
    }

    @Override
    public boolean updateParticipant(String refNumber, Long phoneNumber, String address) {
        Participant existingParticipant = registryRepository.remove(refNumber);
        if (existingParticipant == null) return false;
        else {
            if (phoneNumber != null) existingParticipant.setPhoneNumber(phoneNumber);
            if (address != null) existingParticipant.setAddress(address);
            registryRepository.add(existingParticipant);
            return true;
        }
    }

    @Override
    public boolean removeParticipant(String refNumber) {
        Participant removedParticipant = registryRepository.remove(refNumber);
        return removedParticipant != null;
    }
}
