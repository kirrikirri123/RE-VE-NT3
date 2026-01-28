package com.ahlenius.revent3fx.repository;

import com.ahlenius.revent3fx.entity.MascoteCostume;
import com.ahlenius.revent3fx.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.Optional;

public class MascoteCostumeRepoImpl implements MascoteCostumeRepo{
    private final SessionFactory sessionFactory;

    public MascoteCostumeRepoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    // Create - save
    @Override
    public void saveItem(MascoteCostume mC) {
        try(Session session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.persist(mC);
            transaction.commit();
        }
    }
    @Override
    public void removeItem(MascoteCostume mC) {
        try(Session session =sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            session.remove(mC);
            transaction.commit();
        }
    }
    @Override
    public MascoteCostume updateItem(MascoteCostume mC){
        try(Session session= sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.merge(mC);
            transaction.commit();
            return mC;
        }}

    @Override // Hitta produkt utifrån Id för att kunna matcha mot rental_type sen?
    public Optional<MascoteCostume> findById(long id) {
        try(Session session = sessionFactory.openSession()){
            return Optional.ofNullable(
                    session.get(MascoteCostume.class, id)
            );}
    }
    public MascoteCostume findProductByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from MascoteCostume m where m.productName = :name", MascoteCostume.class)
                    .setParameter("name", name).uniqueResult();
        }
    }

}
