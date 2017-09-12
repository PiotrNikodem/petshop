package com.globallogic.dao;

import com.globallogic.model.animals.Animal;
import com.globallogic.model.animals.Cat;
import com.globallogic.model.animals.Hamster;
import com.globallogic.model.animals.Lizard;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AnimalDaoImpl implements AnimalDao {
    // TODO: 9/12/2017 remove boilerplate :) transactional + ses.factory bean

    @Override
    public void save(Animal animal) {
        Session session = getSession();
        Transaction t = session.beginTransaction();
        session.persist(animal);
        t.commit();
        session.close();
    }

    @Override
    public Animal getAnimal(int id) {
        Session session = getSession();
        session.beginTransaction();
        return session.get(Animal.class, id);
    }

    @Override
    public List<? extends Animal> getList() {
        Session session = getSession();
        session.beginTransaction();
        Query query = session.createQuery("from Animal" );
        List<Animal> list = query.list();
        session.close();
        return list;
    }

    @Override
    public void deleteAnimal(int id) {
        Session session = getSession();
        session.beginTransaction();
        Animal animal = session.get(Animal.class, id);
        session.delete(animal);
        session.flush();
        session.close();
    }

    public List<Hamster> getHamsterList() {
        Session session = getSession();
        session.beginTransaction();
        Query query = session.createQuery("from Animal where type = :type");
        query.setParameter("type", "Hamster");
        List<Hamster> list = query.list();
        session.close();
        return list;
    }
    public List<Cat> getCatList() {
        Session session = getSession();
        session.beginTransaction();
        Query query = session.createQuery("from Animal where type = :type");
        query.setParameter("type", "Cat");
        List<Cat> list = query.list();
        session.close();
        return list;
    }

    public List<Lizard> getLizardList() {
        Session session = getSession();
        session.beginTransaction();
        Query query = session.createQuery("from Animal where type = :type");
        query.setParameter("type", "Lizard");
        List<Lizard> list = query.list();
        session.close();
        return list;
    }

    private static Session getSession() {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        return factory.getCurrentSession();
    }
}
