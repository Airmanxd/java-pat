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

    @GetMapping(value="/departure/{departureid}/postoffice")
    public @ResponseBody PostOffice getPostOfficeByDeparture(@PathVariable("departureid") int departureid){
        return this.getPostOfficeByDeparture(departureid);
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

    public PostOffice getPostOfficeByDeparture(Integer id) {
        return session.createQuery("from Dog where id = :id", Departure.class)
                .setParameter("id",id).getSingleResult().getPostOffice();
    }
    @GetMapping(value = "/sortedBy{fieldName}")
    public @ResponseBody List<PostOffice> getSortedPostOfficesByField(@PathVariable("fieldName") String fieldName) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<PostOffice> postOfficeCriteriaQuery = builder.createQuery(PostOffice.class);
        Root<PostOffice> root = postOfficeCriteriaQuery.from(PostOffice.class);
        postOfficeCriteriaQuery.select(root).orderBy(builder.asc(root.get(fieldName)));
        Query<PostOffice> query = session.createQuery(postOfficeCriteriaQuery);
        return query.getResultList();
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
