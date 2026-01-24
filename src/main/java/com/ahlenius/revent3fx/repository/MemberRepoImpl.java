package com.ahlenius.revent3fx.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MemberRepoImpl implements MemberRepo{
    private final SessionFactory sessionfactory;

    public MemberRepoImpl(SessionFactory sessionfactory) {
        this.sessionfactory = sessionfactory;
    }
} //CRUD
