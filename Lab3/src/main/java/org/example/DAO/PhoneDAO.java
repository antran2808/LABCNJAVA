package org.example.DAO;

import org.example.entity.Phone;
import org.hibernate.ObjectNotFoundException;
import org.example.utils.HibernateUtils;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;


public class PhoneDAO implements Repository<Phone, String> {

    private final Session session = HibernateUtils.getSessionFactory().openSession();

    @Override
    public String add(Phone item) {
        session.beginTransaction();
        String id = (String) session.save(item);
        session.getTransaction().commit();
        return id;
    }

    @Override
    public List<Phone> readAll() {
        Query<Phone> query = session.createQuery("FROM Phone", Phone.class);
        return query.list();
    }

    @Override
    public Phone read(String id) {
        return session.get(Phone.class, id);
    }

    @Override
    public boolean update(Phone item) {
        Query query = session.createQuery("UPDATE Phone SET name = :name, price = :price, color = :color, quantity = :quantity WHERE id = :id");
        query.setParameter("name", item.getName());
        query.setParameter("price", item.getPrice());
        query.setParameter("color", item.getColor());
        query.setParameter("quantity", item.getQuantity());
        query.setParameter("id", item.getId());
        int re = query.executeUpdate();
        session.getTransaction().commit();
        if (re > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        session.beginTransaction();
        try {
            String hql = "DELETE FROM Phone WHERE id = " + id;
            Query query = session.createQuery(hql);
            int affectedRows = query.executeUpdate();
            session.getTransaction().commit();
            return  true;
        } catch (ObjectNotFoundException e) {
            System.out.println("Fail to find and delete: " + id);
            return false;
        }
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
