package com.ahlenius.revent3fx.repository;

import com.ahlenius.revent3fx.entity.BouncyCastle;
import com.ahlenius.revent3fx.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public class BouncyCastleRepoImpl implements BouncyCastleRepo {
    private final SessionFactory sessionFactory;

    public BouncyCastleRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveItem(BouncyCastle bC) {
        try(Session session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.persist(bC);
            transaction.commit();
        }
    }
    @Override
    public void removeItem(BouncyCastle bC) {
        try(Session session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.remove(bC);
            transaction.commit();
        }
    }
    @Override
    public BouncyCastle updateItem(BouncyCastle bC){
        try(Session session= sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(bC);
            transaction.commit();
            return bC;
        }}

    @Override // Hitta produkt utifrån Id för att kunna matcha mot rental_type sen?
    public Optional<BouncyCastle> findById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(
                    session.get(BouncyCastle.class, id)
            );
        }
    }
    public BouncyCastle findProductByName(String name){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("from BouncyCastle m where m.productName = :name", BouncyCastle.class)
                    .setParameter("name", name).uniqueResult();
        }
    }
}