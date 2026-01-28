package com.ahlenius.revent3fx.repository;

import com.ahlenius.revent3fx.entity.BouncyCastle;
import com.ahlenius.revent3fx.entity.DiscoMachine;
import com.ahlenius.revent3fx.entity.MascoteCostume;

import java.util.Optional;

public interface DiscoMachineRepo {

    void saveItem(DiscoMachine discoMachine);

    void removeItem(DiscoMachine discoMachine);

    DiscoMachine updateItem(DiscoMachine discoMachine);

    Optional<DiscoMachine> findById(long id);
}
