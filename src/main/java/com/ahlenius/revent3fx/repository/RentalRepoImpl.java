package com.ahlenius.revent3fx.repository;

import com.ahlenius.revent3fx.entity.Rental;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class RentalRepoImpl implements RentalRepo{
    private final SessionFactory sessionFactory;

    public RentalRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveRental(Rental rental) {
        try(Session session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.persist(rental);
            transaction.commit();
        }
    }

    @Override
    public void removeRental(Rental rental) {
        try(Session session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.remove(rental);
            transaction.commit();
        }
    }

    public Rental updateRental(Rental rental){
        try(Session session= sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(rental);
            transaction.commit();
            return rental;
        }}
}
