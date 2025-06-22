/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inventory.management.system;

/**
 *
 * @author dell
 */
import java.util.*;

public class Product {

    static class ProductRecord {
        int productId;
        String productName;
        int quantity;
        double price;

        ProductRecord(int productId, String productName, int quantity, double price) {
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
        }

        void display() {
            System.out.println("Product ID: " + productId + ", Name: " + productName +
                    ", Quantity: " + quantity + ", Price: Rs " + price);
        }
    }

    static HashMap<Integer, ProductRecord> inventory = new HashMap<>();

    public static long addProduct(ProductRecord product) {
        long start = System.nanoTime();
        inventory.put(product.productId, product); 
        long end = System.nanoTime();
        return end - start;
    }

    public static long updateProduct(int id, int newQty, double newPrice) {
        long start = System.nanoTime();
        if (inventory.containsKey(id)) {
            ProductRecord p = inventory.get(id);
            p.quantity = newQty;
            p.price = newPrice;
        } else {
            System.out.println("Product not found.");
        }
        long end = System.nanoTime();
        return end - start;
    }

    public static long deleteProduct(int id) {
        long start = System.nanoTime();
        inventory.remove(id); 
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Inventory Management System ---");
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. View All Products");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();

                    ProductRecord newProduct = new ProductRecord(id, name, qty, price);
                    long time = addProduct(newProduct);
                    System.out.println("Product added.");
                    System.out.println("Time taken (Add): " + time + " ns ");
                }

                case 2 -> {
                    System.out.print("Enter Product ID to update: ");
                    int id = sc.nextInt();
                    System.out.print("Enter new Quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Enter new Price: ");
                    double price = sc.nextDouble();

                    long time = updateProduct(id, qty, price);
                    System.out.println("Product updated (if found).");
                    System.out.println("Time taken (Update): " + time + " ns ");
                }

                case 3 -> {
                    System.out.print("Enter Product ID to delete: ");
                    int id = sc.nextInt();
                    long time = deleteProduct(id);
                    System.out.println("Product deleted (if existed).");
                    System.out.println("Time taken (Delete): " + time + " ns ");
                }

                case 4 -> {
                    if (inventory.isEmpty()) {
                        System.out.println("Inventory is empty.");
                    } else {
                        System.out.println("Inventory:");
                        for (ProductRecord p : inventory.values()) {
                            p.display();
                        }
                    }
                }

                case 5 -> System.out.println("Exiting.");

                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 5);

        sc.close();
    }
}
