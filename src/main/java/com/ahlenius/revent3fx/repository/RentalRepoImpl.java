package com.ahlenius.revent3fx.repository;

import org.hibernate.SessionFactory;

public class RentalRepoImpl implements RentalRepo{
    private final SessionFactory sessionFactory;

    public RentalRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
