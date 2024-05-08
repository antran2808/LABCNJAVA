package org.example.lab5.DAO;

import org.example.lab5.Repository.Repository;
import org.example.lab5.Utils.HibernateUtils;
import org.example.lab5.models.Product;
import org.example.lab5.models.User;
import org.hibernate.Session;

import java.util.List;

public class ProductDAO implements Repository<Product, Long> {

    @Override
    public Long add(Product item) {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            session.beginTransaction();
            Long productID = (Long) session.save(item);
            session.getTransaction().commit();
            return productID;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public List<Product> getAll() {
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            session.beginTransaction();
            List<Product> productList = session.createQuery("FROM Product", Product.class).list();
            session.getTransaction().commit();
            return productList;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public Product get(Long id) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            session.beginTransaction();
            Product product = session.find(Product.class,id);
            session.getTransaction().commit();
            return product;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(Product item) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
            return true;
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean remove(Product item) {
        try (Session session = HibernateUtils.getSessionFactory().openSession()){
            session.beginTransaction();
            session.delete(item);
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
        try (Session session = HibernateUtils.getSessionFactory().openSession();) {
            session.beginTransaction();
            Product product = session.find(Product.class, id);
            session.delete(product);
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
