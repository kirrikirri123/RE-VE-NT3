package com.ahlenius.revent3fx.repository;

import com.ahlenius.revent3fx.entity.BouncyCastle;
import com.ahlenius.revent3fx.entity.MascoteCostume;

import java.util.Optional;

public interface MascoteCostumeRepo {

    void saveItem(MascoteCostume mascoteCostume);

    void removeItem(MascoteCostume mascoteCostume);

    MascoteCostume updateItem(MascoteCostume mascoteCostume);

    Optional<MascoteCostume> findById(long id);

}
