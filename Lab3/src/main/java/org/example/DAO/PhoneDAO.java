package org.example.DAO;

import org.example.entity.Phone;
import org.example.utils.HibernateUtils;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class PhoneDAO implements Repository<Phone, Long> {
    private final Session session = HibernateUtils.getSessionFactory().openSession();

    @Override
    public Long add(Phone item){
        Long id = (Long) session.save(item);
        //session.close();
        return id;
    }

    @Override
    public List<Phone> readAll()  {
        //session.beginTransaction();
        Query query = session.createQuery("from Phone");
        List<Phone> phones = query.list();
        //session.close();
        return phones;
    }

    @Override
    public Phone read(Long id)  {
        return session.get(Phone.class, id);
    }

    @Override
    public boolean update(Phone item)  {
        session.beginTransaction();
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
    public boolean delete(Long id)  {
        session.beginTransaction();
        try {
            String hql = "DELETE FROM Phone WHERE id = " + id;
            Query query = session.createQuery(hql);
            int affectedRows = query.executeUpdate();
            session.getTransaction().commit();
            return  true;
        } catch (ObjectNotFoundException e) {
            System.out.println("Lỗi: Không tìm thấy bản ghi với ID " + id);
            return false;
        }
    }
    public Phone hightestPrice()  {
        List<Phone> phones = readAll();
        int price = 0;
        Phone phone = null;
        for(Phone p: phones){
            if(p.getPrice() > price){
                price = p.getPrice();
                phone = p;
            }
        }
        System.out.println("Hightest price: " + phone.toString());
        return phone;
    }
    public List<Phone> listPhoneByCountry(String name)  {
        String hql = "FROM Phone ORDER BY price ASC";
        Query query = session.createQuery(hql);
        List<Phone> phones =  query.list();
        List<Phone> listPhone = new ArrayList<>();
        for(Phone p: phones){
            if(p.getManufacture().getLocation().equals(name)){
                listPhone.add(p);
            }
        }
        return listPhone;
    }
    public Phone overMillion()  {
        List<Phone> phones = readAll();
        Phone phone = null;
        for(Phone p: phones){
            if(p.getPrice() > 50000000){
                phone = p;
            }
        }
        if(phone != null){
            System.out.println("Phone priced above 50 million VND: " + phone.toString());
        }else{
            System.out.println("Not exist.");
        }
        return phone;
    }
    public Phone firstPhone(){
        String hql = "FROM Phone WHERE color = 'hong' AND price > 15000000";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        List<Phone> phone = query.list();
        if( phone.size() >= 1 ){
            return phone.get(0);
        }else{
            return null;
        }
    }
}
