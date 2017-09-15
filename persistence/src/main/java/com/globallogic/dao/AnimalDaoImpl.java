package com.globallogic.dao;

import com.globallogic.model.animals.Animal;
import com.globallogic.model.animals.Cat;
import com.globallogic.model.animals.Hamster;
import com.globallogic.model.animals.Lizard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AnimalDaoImpl implements AnimalDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(Animal animal) {
        Session session = sessionFactory.getCurrentSession();
        Transaction t = session.beginTransaction();
        session.persist(animal);
        t.commit();
        session.close();
    }

    @Override
    public Animal getAnimal(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        return session.get(Animal.class, id);
    }

    @Override
    public List<? extends Animal> getList() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Animal" );
        return query.list();
    }

    @Override
    public void deleteAnimal(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Animal animal = session.get(Animal.class, id);
        session.delete(animal);
        session.flush();
    }
    @Override
    public List<Hamster> getHamsterList() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Animal where type = :type");
        query.setParameter("type", "Hamster");
        return query.list();

    }
    @Override
    public List<Cat> getCatList() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Animal where type = :type");
        query.setParameter("type", "Cat");
        return query.list();
    }
    @Override
    public List<Lizard> getLizardList() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Animal where type = :type");
        query.setParameter("type", "Lizard");
        return query.list();
    }
}
