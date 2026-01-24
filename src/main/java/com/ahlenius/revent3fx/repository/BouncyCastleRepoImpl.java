package com.ahlenius.revent3fx.repository;

import org.hibernate.SessionFactory;

public class BouncyCastleRepoImpl implements BouncyCastleRepo {
    private final SessionFactory sessionFactory;

    public BouncyCastleRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
