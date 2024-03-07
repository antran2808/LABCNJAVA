package org.example;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
public class ManufactureDAO implements Repository<Manufacture, String> {
    private Session session;

    public ManufactureDAO(Session session) {
        this.session = session;
    }



    public String add(Manufacture item) {
        session.beginTransaction();
        String id = (String) session.save(item);
        session.getTransaction().commit();
        return id;
    }

    public List<Manufacture> readAll() {
        Query<Manufacture> query = session.createQuery("FROM Manufacture", Manufacture.class);
        return query.list();
    }

    public Manufacture read(String id) {
        return session.get(Manufacture.class, id);
    }

    public boolean update(Manufacture item) {
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        return true;
    }

    public boolean delete(String id) {
        session.beginTransaction();
        Manufacture manufacture = session.get(Manufacture.class, id);
        if (manufacture != null) {
            session.delete(manufacture);
            session.getTransaction().commit();
            return true;
        }
        session.getTransaction().rollback();
        return false;
    }

    public boolean areAllManufacturesAboveEmployeeCount(int employeeCount) {
        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Manufacture WHERE employeeCount <= :employeeCount", Long.class);
        query.setParameter("employeeCount", employeeCount);
        Long count = query.uniqueResult();
        return count == 0;
    }

    public int getSumOfEmployees() {
        Query<Integer> query = session.createQuery("SELECT SUM(employeeCount) FROM Manufacture", Integer.class);
        Integer sum = query.uniqueResult();
        return sum != null ? sum : 0;
    }

    public Manufacture getLastManufactureBasedInUS() {
        String hql = "FROM Manufacturer WHERE country = :country ORDER BY id DESC";
        Query<Manufacture> query = session.createQuery(hql, Manufacture.class);
        query.setParameter("country", "US");
        query.setMaxResults(1);
        List<Manufacture> manufacturers = query.getResultList();

        if (manufacturers.isEmpty()) {
            throw new InvalidOperationException("No manufacturer based in the US found.");
        }

        return manufacturers.get(0);
    }
}
