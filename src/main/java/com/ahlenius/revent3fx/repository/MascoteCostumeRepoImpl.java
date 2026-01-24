package com.ahlenius.revent3fx.repository;

import org.hibernate.SessionFactory;

public class MascoteCostumeRepoImpl implements MascoteCostumeRepo{
    private final SessionFactory sessionFactory;

    public MascoteCostumeRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
