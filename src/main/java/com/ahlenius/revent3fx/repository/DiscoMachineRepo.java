package com.ahlenius.revent3fx.repository;

import com.ahlenius.revent3fx.entity.DiscoMachine;

import java.util.Optional;

public interface DiscoMachineRepo {

    void saveItem(DiscoMachine discoMachine);

    void removeItem(DiscoMachine discoMachine);

    DiscoMachine updateItem(DiscoMachine discoMachine);

    Optional<DiscoMachine> findById(long id);
}
