package com.example.Practice15;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

@Controller
public class PostOfficeController {
    private SessionFactory sessionFactory;
    private Session session;

    @PostConstruct
    void init() {
        session = sessionFactory.openSession();
    }

    public void save(Departure departure) {
        session.beginTransaction();
        session.save(departure);
        session.getTransaction().commit();
    }
    public void remove(int id) {
        session.beginTransaction();
        Departure departure = session.find(Departure.class, id);
        session.remove(departure);
        session.getTransaction().commit();
    }

    public List<Departure> getAll() {
        List<Departure> list = session.createQuery("select d from Departure d", Departure.class).getResultList();
        return list;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @PreDestroy
    void destroy() {
        sessionFactory.close();
    }
}
