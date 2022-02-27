package com.newbeginnings.registry.repository;

import com.newbeginnings.registry.model.Participant;

import java.util.Collection;

public interface RegistryRepository {

    public Collection<Participant> retrieveAll();
    public Participant retrieve(String refNumber);
    public void add(Participant participant);
    public Participant remove(String refNumber);
}
