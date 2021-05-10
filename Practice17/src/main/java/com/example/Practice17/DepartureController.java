package com.example.Practice17;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Controller
public class DepartureController {
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

    @GetMapping(value = "/departuresSortedBy{fieldName}")
    public @ResponseBody
    List<Departure> getSortedDeparturesByField(@PathVariable("fieldName") String fieldName) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Departure> departureCriteriaQuery = builder.createQuery(Departure.class);
        Root<Departure> root = departureCriteriaQuery.from(Departure.class);
        departureCriteriaQuery.select(root).orderBy(builder.asc(root.get(fieldName)));
        Query<Departure> query = session.createQuery(departureCriteriaQuery);
        return query.getResultList();
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
