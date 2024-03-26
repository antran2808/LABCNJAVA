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

public class ManufactureDAO implements Repository<Manufacture, Long> {
    private final Session session = HibernateUtils.getSessionFactory().openSession();
    @Override
    public Long add(Manufacture item)  {
        Long id = (Long) session.save(item);
        //session.close();
        return id;
    }

    @Override
    public List<Manufacture> readAll()  {
        Query query = session.createQuery("from Manufacture");
        List<Manufacture> manufactures = query.list();
        //session.close();
        return manufactures;
    }

    @Override
    public Manufacture read(Long id)  {
        return session.get(Manufacture.class, id);
    }

    @Override
    public boolean update(Manufacture item)  {
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
    public boolean delete(Long id)  {
        session.beginTransaction();
        try {
            String hql = "DELETE FROM Manufacture WHERE id = " + id;
            Query query = session.createQuery(hql);
            int affectedRows = query.executeUpdate();
            session.getTransaction().commit();
            return  true;
        } catch (ObjectNotFoundException e) {
            System.out.println("Lỗi: Không tìm thấy bản ghi với ID " + id);
            return false;
        }
    }
    public List<Manufacture> over100Employee(){
        String hql = "FROM Manufacture WHERE employee > 100";
        Query query = session.createQuery(hql);
        List<Manufacture> manufactures = query.list();
        return manufactures;
    }
    public int countEmployee()  {
        List<Manufacture> manufactures = readAll();
        int count  = 0;
        for(Manufacture manufacture: manufactures){
            count += manufacture.getEmployee();
        }
        return count;
    }
    public Manufacture lastCountry(){
        String hql = "FROM Manufacture WHERE location = 'USA' ORDER BY id DESC";
        Query query = session.createQuery(hql);
        List<Manufacture> manufactures = query.list();
        if(manufactures.isEmpty()){
            throw new InvalidOperationException("No Manufacture in USA");
        }
        return manufactures.get(0);
    }
}
