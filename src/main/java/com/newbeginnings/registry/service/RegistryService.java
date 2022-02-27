package com.newbeginnings.registry.service;

import com.newbeginnings.registry.model.Participant;

import java.util.Collection;

public interface RegistryService {

    public Collection<Participant> getAllParticipants();
    public Participant getParticipant(String refNumber);
    public void addParticipant(Participant participant);
    public boolean updateParticipant(Participant particpant);
    public boolean updateParticipant(String refNumber, Long phoneNumber, String address);
    public boolean removeParticipant(String refNumber);
}
