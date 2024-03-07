package org.example;

import org.example.DAO.InvalidOperationException;
import org.example.DAO.ManufactureDAO;
import org.example.DAO.PhoneDAO;
import org.example.Entity.Manufacture;
import org.example.Entity.Phone;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class Program {
    public static void main(String[] args) {
        // Khởi tạo SessionFactory từ HibernateUtils
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

        // Mở phiên làm việc Hibernate
        Session session = sessionFactory.openSession();

        // Khởi tạo PhoneDAO và ManufactureDAO
        PhoneDAO phoneDAO = new PhoneDAO(session);
        ManufactureDAO manufactureDAO = new ManufactureDAO(session);

        try {

            // Thêm một sản phẩm mới
            Phone newPhone = new Phone("Phone X", 50000000, "Black");
            String newProductId = phoneDAO.add(newPhone);
            System.out.println("Added product with ID: " + newProductId);

            // Đọc danh sách sản phẩm
            System.out.println("Product list:");
            List<Phone> phones = phoneDAO.readAll();
            for (Phone phone : phones) {
                System.out.println(phone);
            }

            // Đọc một sản phẩm theo id
            String productId = "1";
            Phone phoneById = phoneDAO.getById(productId);
            System.out.println("Product with ID " + productId + ": " + phoneById);

            // Cập nhật một sản phẩm
            String updateProductId = "2";
            Phone updatedPhone = phoneDAO.getById(updateProductId);
            updatedPhone.setName("Phone Y");
            phoneDAO.update(updatedPhone);
            System.out.println("Updated product with ID " + updateProductId + ": " + updatedPhone);

            // Xóa một sản phẩm
            String deleteProductId = "3"    ;
            phoneDAO.delete(deleteProductId);
            System.out.println("Deleted product with ID: " + deleteProductId);

            // Lấy điện thoại có giá bán cao nhất
            Phone phoneWithHighestPrice = phoneDAO.getPhoneWithHighestSellingPrice();
            System.out.println("Phone with highest selling price: " + phoneWithHighestPrice);

            // Lấy danh sách điện thoại sắp xếp theo tên quốc gia, nếu hai điện thoại có cùng quốc gia thì sắp xếp giảm dần theo giá
            List<Phone> phonesSortedByCountry = phoneDAO.getPhonesSortedByCountryName();
            System.out.println("Phones sorted by country name:");
            for (Phone phone : phonesSortedByCountry) {
                System.out.println(phone);
            }

            // Kiểm tra xem có điện thoại có giá trên 50 triệu VND hay không
            boolean hasPhoneAbove50Million = phoneDAO.hasPhonePricedAbove50Million();
            System.out.println("Is there any phone above 50 million VND? " + hasPhoneAbove50Million);

            // Lấy điện thoại đầu tiên trong danh sách thỏa mãn: có màu 'Pink' và giá trên 15 triệu
            Phone firstMatchingPhone = phoneDAO.getFirstPhoneWithColorAndPriceCriteria("Pink", 15000000);
            System.out.println("First matching phone: " + firstMatchingPhone);

            // Kiểm tra xem tất cả nhà sản xuất có hơn 100 nhân viên không
            boolean areAllManufacturersAbove100Employees = manufactureDAO.areAllManufacturesAboveEmployeeCount(100);
            System.out.println("Are all manufacturers above 100 employees? " + areAllManufacturersAbove100Employees);

            // Lấy tổng số nhân viên của tất cả nhà sản xuất
            int sumOfEmployees = manufactureDAO.getSumOfEmployees();
            System.out.println("Sum of employees: " + sumOfEmployees);

            // Lấy nhà sản xuất cuối cùng đáp ứng tiêu chí: có trụ sở tại Mỹ
            Manufacture lastManufacturerBasedInUS = manufactureDAO.getLastManufactureBasedInUS();
            if (lastManufacturerBasedInUS != null) {
                System.out.println("Last manufacturer based in the US: " + lastManufacturerBasedInUS.getName());
            } else {
                throw new InvalidOperationException("No manufacturer based in the US found.");
            }
        } catch (InvalidOperationException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Đóng phiên làm việc Hibernate và shutdown HibernateUtils
            session.close();
            HibernateUtils.shutdown();
        }
    }
}
