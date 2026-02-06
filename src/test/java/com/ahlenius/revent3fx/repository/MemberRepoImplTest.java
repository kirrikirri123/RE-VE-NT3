package com.ahlenius.revent3fx.repository;

import com.ahlenius.revent3fx.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepoImplTest {
    //Integrationstest

    private static SessionFactory sessionFactory;

    private MemberRepoImpl memberRepo;

    @BeforeAll
    static void beforeAll(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @AfterAll
    static void afterAll(){
        HibernateUtil.shutdown();
    }

    @BeforeEach
    void setUo(){
        memberRepo = new MemberRepoImpl(sessionFactory);
    }


    @Test
    void saveMember() {
    }


    @Test
    void findMemberByEmail() {
    }
}