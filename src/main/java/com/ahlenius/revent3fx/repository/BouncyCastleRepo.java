package com.ahlenius.revent3fx.repository;

import com.ahlenius.revent3fx.entity.BouncyCastle;

import java.util.Optional;

public interface BouncyCastleRepo {

    void saveItem(BouncyCastle bouncyCastle);

    void removeItem(BouncyCastle bouncyCastle);

    BouncyCastle updateItem(BouncyCastle bouncyCastle);

    Optional<BouncyCastle> findById(long id);
}
