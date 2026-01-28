package com.ahlenius.revent3fx.repository;

import com.ahlenius.revent3fx.entity.Costume;

import java.util.Optional;

public interface MascoteCostumeRepo {

    void saveItem(Costume costume);

    void removeItem(Costume costume);

    Costume updateItem(Costume costume);

    Optional<Costume> findById(long id);

}
