package org.example.lab5.DAO;

import org.example.lab5.Repository.Repository;
import org.example.lab5.Utils.HibernateUtils;
import org.example.lab5.models.Product;
import org.example.lab5.models.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAO implements Repository<User, Long> {
    @Override
    public Long add(User user) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            Long userID = (Long) session.save(user);
            session.getTransaction().commit();
            return userID;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<User> userList = session.createQuery("FROM User", User.class).list();
            session.getTransaction().commit();
            return userList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public User get(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            session.beginTransaction();
            User user = session.find(User.class,id);
            session.getTransaction().commit();
            return user;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(User user) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean remove(User user) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean removeById(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            session.beginTransaction();
            User user = session.find(User.class,id);
            session.delete(user);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public User findByUsername(String username) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            session.beginTransaction();
            Query query= session.createQuery("from User where username=:username");
            query.setParameter("username", username);
            User user = (User)query.uniqueResult();
            session.getTransaction().commit();
            return user;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
