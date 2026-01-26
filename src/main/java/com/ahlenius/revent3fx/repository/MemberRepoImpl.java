package com.ahlenius.revent3fx.repository;

import com.ahlenius.revent3fx.entity.Member;
import com.ahlenius.revent3fx.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class MemberRepoImpl implements MemberRepo{
    private final SessionFactory sessionfactory;

    public MemberRepoImpl(SessionFactory sessionfactory) {
        this.sessionfactory = sessionfactory;
    }
    //CRUD
    @Override // C- Create
    public void saveMember(Member member) {
        try(Session session = sessionfactory.openSession()){
            var transaction = session.beginTransaction();
             session.persist(member);
             transaction.commit();

        }
    }
     // R - Read
        public Member findMemberByName(String name){
         try(Session session = HibernateUtil.getSessionFactory().openSession()){
             return session.createQuery("from Member m where m.fName = :name", Member.class)
                     .setParameter("name", name).uniqueResult();
         }
        }


    }

