package com.ahlenius.revent3fx.repository;

import com.ahlenius.revent3fx.entity.Costume;
import com.ahlenius.revent3fx.entity.Member;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Optional;

public class MascoteCostumeRepoImpl implements MascoteCostumeRepo{
    private final SessionFactory sessionFactory;

    public MascoteCostumeRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    // Create - save
    @Override
    public void saveItem(Costume mC) {
        try(Session session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.persist(mC);
            transaction.commit();
        }
    }
    @Override
    public void removeItem(Costume mC) {
        try(Session session =sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.remove(mC);
            transaction.commit();
        }
    }
    @Override
    public Costume updateItem(Costume mC){
        try(Session session= sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(mC);
            transaction.commit();
            return mC;
        }}

    @Override // Hitta produkt utifrån Id för att kunna matcha mot rental_type sen?
    public Optional<Costume> findById(long id) {
        try(Session session = sessionFactory.openSession()){
            return Optional.ofNullable(
                    session.get(Costume.class, id)
            );}
    }
    public Optional<Costume> findProductByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Costume m where m.productName = :name", Costume.class)
                    .setParameter("name", name).uniqueResultOptional();
        }
    }
    public List<Costume> findAllItems() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Costume", Costume.class).getResultList();
        }

    }

}
