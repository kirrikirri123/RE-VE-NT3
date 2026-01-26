package com.ahlenius.revent3fx.repository;

import com.ahlenius.revent3fx.entity.DiscoMachine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Optional;

public class DiscoMachineRepoImpl implements DiscoMachineRepo{
    private final SessionFactory sessionFactory;

    public DiscoMachineRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    // Create - save
    @Override
    public void saveItem(DiscoMachine dM) {
        try(Session session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.persist(dM);
            transaction.commit();
        }
    }
    @Override
    public void removeItem(DiscoMachine dM) {
        try(Session session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.remove(dM);
            transaction.commit();
    }
    }
    @Override
    public DiscoMachine updateItem(DiscoMachine dM){
        try(Session session= sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(dM);
            transaction.commit();
            return dM;
        }}
    @Override // Hitta produkt utifrån Id för att kunna matcha mot rental_type sen?
    public Optional<DiscoMachine> findById(long id) {
        try(Session session = sessionFactory.openSession()){
            return Optional.ofNullable(
                    session.get(DiscoMachine.class, id)
            );}
    }

    public DiscoMachine findProductByName(String name){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("from DiscoMachine m where m.productName = :name", DiscoMachine.class)
                    .setParameter("name", name).uniqueResult();
        }
    }



}

