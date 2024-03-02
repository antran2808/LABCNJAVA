package org.example;

import java.util.List;
import java.util.Scanner;

public class Program {
    private static final ProductDAO productDAO = new ProductDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Read product list");
            System.out.println("2. Read a product by id");
            System.out.println("3. Add a new product");
            System.out.println("4. Update a product");
            System.out.println("5. Delete a product");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            productDAO.useTable("products");

            switch (choice) {
                case 1:
                    readProductList();
                    break;
                case 2:
                    readProductById(scanner);
                    break;
                case 3:
                    addNewProduct(scanner);
                    break;
                case 4:
                    updateProduct(scanner);
                    break;
                case 5:
                    deleteProduct(scanner);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void readProductList() {
        List<Product> products = productDAO.readAll();
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void readProductById(Scanner scanner) {
        System.out.print("Enter product id: ");
        int id = scanner.nextInt();
        Product product = productDAO.read(id);
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void addNewProduct(Scanner scanner) {
        System.out.print("Enter product name: ");
        String name = scanner.next();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();

        Product product = new Product(name, price);
        Integer generatedId = productDAO.add(product);
        if (generatedId != null) {
            System.out.println("Product added with id: " + generatedId);
        } else {
            System.out.println("Failed to add product.");
        }
    }

    private static void updateProduct(Scanner scanner) {
        System.out.print("Enter product id: ");
        int id = scanner.nextInt();
        Product product = productDAO.read(id);
        if (product != null) {
            System.out.print("Enter new product name: ");
            String name = scanner.next();
            System.out.print("Enter new product price: ");
            double price = scanner.nextDouble();

            product.setName(name);
            product.setPrice(price);
            boolean success = productDAO.update(product);
            if (success) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("Failed to update product.");
            }
        } else {
            System.out.println("Product not found.");
        }
    }

    private static void deleteProduct(Scanner scanner) {
        System.out.print("Enter product id: ");
        int id = scanner.nextInt();
        boolean success = productDAO.delete(id);
        if (success) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Failed to delete product.");
        }
    }
}
