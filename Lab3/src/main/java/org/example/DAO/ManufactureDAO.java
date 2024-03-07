package org.example.DAO;

import org.example.DAO.Repository;
import org.example.entity.Manufacture;
import org.example.entity.Phone;
import org.example.utils.HibernateUtils;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class ManufactureDAO implements Repository<Manufacture, String> {
    private final Session session = HibernateUtils.getSessionFactory().openSession();

    @Override
    public String add(Manufacture item) {
        session.beginTransaction();
        String id = (String) session.save(item);
        session.getTransaction().commit();
        return id;
    }

    @Override
    public List<Manufacture> readAll() {
        Query<Manufacture> query = session.createQuery("FROM Manufacture", Manufacture.class);
        return query.list();
    }

    @Override
    public Manufacture read(String id) {
        return session.get(Manufacture.class, id);
    }

    @Override
    public boolean update(Manufacture item) {
        session.beginTransaction();
        Query query = session.createQuery("UPDATE Manufacture SET name = :name, location = :location, employee = :employee WHERE id = :id");
        query.setParameter("name", item.getName());
        query.setParameter("location", item.getLocation());
        query.setParameter("employee", item.getEmployee());
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
            String hql = "DELETE FROM Manufacture WHERE id = " + id;
            Query query = session.createQuery(hql);
            int affectedRows = query.executeUpdate();
            session.getTransaction().commit();
            return  true;
        } catch (ObjectNotFoundException e) {
            System.out.println("Fail to find and delete: " + id);
            return false;
        }
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
