package org.example;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class PhoneDAO implements Repository<Phone, String> {

    private Session session;

    public PhoneDAO(Session session) {
        this.session = session;
    }

    public String add(Phone item) {
        session.beginTransaction();
        String id = (String) session.save(item);
        session.getTransaction().commit();
        return id;
    }

    public List<Phone> readAll() {
        Query<Phone> query = session.createQuery("FROM Phone", Phone.class);
        return query.list();
    }

    public Phone read(String id) {
        return session.get(Phone.class, id);
    }

    public boolean update(Phone item) {
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        return true;
    }

    public boolean delete(String id) {
        session.beginTransaction();
        Phone phone = session.get(Phone.class, id);
        if (phone != null) {
            session.delete(phone);
            session.getTransaction().commit();
            return true;
        }
        session.getTransaction().rollback();
        return false;
    }

    public Phone getById(String id) {
        return session.get(Phone.class, id);
    }

    public Phone getPhoneWithHighestSellingPrice() {
        Query<Phone> query = session.createQuery("FROM Phone ORDER BY price DESC", Phone.class);
        query.setMaxResults(1);
        return query.uniqueResult();
    }

    public List<Phone> getPhonesSortedByCountryName() {
        Query<Phone> query = session.createQuery("FROM Phone ORDER BY country ASC, price DESC", Phone.class);
        return query.list();
    }

    public boolean hasPhonePricedAbove50Million() {
        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Phone WHERE price > 50000000", Long.class);
        Long count = query.uniqueResult();
        return count > 0;
    }

    public Phone getFirstPhoneWithColorAndPriceCriteria(String color, double price) {
        Query<Phone> query = session.createQuery("FROM Phone WHERE color = :color AND price > :price", Phone.class);
        query.setParameter("color", color);
        query.setParameter("price", price);
        query.setMaxResults(1);
        return query.uniqueResult();
    }
}
