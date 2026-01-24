package com.ahlenius.revent3fx.repository;

import org.hibernate.SessionFactory;

public class DiscoMachineRepoImpl implements DiscoMachineRepo{
    private final SessionFactory sessionFactory;

    public DiscoMachineRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
