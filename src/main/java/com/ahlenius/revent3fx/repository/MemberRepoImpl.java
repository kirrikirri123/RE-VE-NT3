package com.ahlenius.revent3fx.repository;

import com.ahlenius.revent3fx.entity.Member;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

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
        public List<Member> findMemberByFname(String fname){
            try(Session session = sessionFactory.openSession()){
                return session.createQuery("from Member m where m.fname = :fname", Member.class)
                        .setParameter("fname",fname).list();
            }
        }
        public List<Member> findAllMembers() {
            try (Session session = sessionFactory.openSession()) {
                return session.createQuery("from Member", Member.class).getResultList();
            }

      }
}


