package com.ahlenius.revent3fx.repository;

import com.ahlenius.revent3fx.entity.Rental;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class RentalRepoImpl implements RentalRepo{
    private final SessionFactory sessionFactory;

    public RentalRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Rental saveRental(Rental rental) {
        try(Session session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.persist(rental);
            transaction.commit();
        }return rental;
    }

    @Override
    public void removeRental(Rental rental) {
        try(Session session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.remove(rental);
            transaction.commit();
        }
    }

    @Override
    public List<Rental> findRentalList() {
        try(Session session = sessionFactory.openSession()){
        return session.createQuery(" FROM Rental"
                 , Rental.class).getResultList();
        }
    }

    public List<Rental> findAvailibaleRentalList(boolean returned) {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("from Rental r where r.returned = :returned", Rental.class)
                    .setParameter("returned",returned)
                    .getResultList();
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
