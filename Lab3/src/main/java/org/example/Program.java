package org.example;

import org.example.DAO.ManufactureDAO;
import org.example.DAO.PhoneDAO;
import org.example.entity.Manufacture;
import org.example.entity.Phone;
import org.example.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.beans.XMLEncoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Program {
    static PhoneDAO phoneDAO = new PhoneDAO();
    static ManufactureDAO manufactureDAO = new ManufactureDAO();
    static Manufacture manufacture;

    public static void main( String[] args ) throws SQLException
    {
        addData();
        showData();
        phoneDAO.hightestPrice();
        System.out.println(phoneDAO.firstPhone());

        for(Manufacture m: manufactureDAO.readAll()){
            System.out.println(m.toString());
        }
        //manufactureDAO.delete(6L);
        for(Manufacture m: manufactureDAO.over100Employee()){
            System.out.println(m.toString());
        }
        System.out.println(manufactureDAO.countEmployee());
        System.out.println(manufactureDAO.lastCountry().toString());
    }
    private static void addData() throws SQLException {
        Phone phone1 = new Phone("ip", 20, "den", 10);
        Phone phone2 = new Phone("ipX", 20, "den", 10);
        List<Phone> phones = new ArrayList<>();
        phones.add(phone1);
        phones.add(phone2);
        manufacture = new Manufacture("apple", "USA", 10);
        manufacture.setPhones(phones);
        phone1.setManufacture(manufacture);
        phone2.setManufacture(manufacture);
        for(Phone p: phones){
            phoneDAO.add(p);
        }
    }
    private static void showData() throws SQLException {
        List<Phone> list = phoneDAO.readAll();
        for(Phone p: list){
            System.out.println(p);
        }
    }
}
