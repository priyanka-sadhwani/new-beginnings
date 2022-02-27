package com.newbeginnings.registry.repository;

import com.newbeginnings.registry.model.Participant;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class SimpleRegistryRepository implements RegistryRepository {

    @Getter
    private Map<String, Participant> register = new HashMap<>();

    @Override
    public Collection<Participant> retrieveAll() {
        return register.values();
    }

    @Override
    public Participant retrieve(String refNumber) {
        return register.get(refNumber);
    }

    @Override
    public void add(Participant participant) {
        register.putIfAbsent(participant.getReferenceNumber(), participant);
    }

    @Override
    public Participant remove(String refNumber) {
        return register.remove(refNumber);
    }
}
