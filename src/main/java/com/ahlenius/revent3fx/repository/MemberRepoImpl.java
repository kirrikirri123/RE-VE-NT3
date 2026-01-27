package com.ahlenius.revent3fx.repository;

import com.ahlenius.revent3fx.entity.Member;
import com.ahlenius.revent3fx.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class MemberRepoImpl implements MemberRepo{
    private final SessionFactory sessionFactory;

    public MemberRepoImpl(SessionFactory sessionfactory) {
        this.sessionFactory = sessionfactory;
    }

    @Override // Spara medeln
    public void saveMember(Member member) {
        try(Session session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
             session.persist(member);
             transaction.commit();
        }
    }

    @Override // Ta bort medlem
    public void deleteMember(Member member) {
        try(Session session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.remove(member);
            transaction.commit();
        }
    }
    public Member updateMember(Member m){
        try(Session session= sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(m);
            transaction.commit();
            return m; // returnerar kopia av uppdaterade objektet inte samma instans?
        }}

         public Member findMemberByEmail(String email){
         try(Session session = sessionFactory.openSession()){
             return session.createQuery("from Member m where m.email = :email", Member.class)
                     .setParameter("email",email).uniqueResult();
         }
        }
        public List<Member> findMemberByFname(String fName){
            try(Session session = sessionFactory.openSession()){
                return session.createQuery("from Member m where m.fName = :fName", Member.class)
                        .setParameter("fname",fName).list();
            }
        }


    }

