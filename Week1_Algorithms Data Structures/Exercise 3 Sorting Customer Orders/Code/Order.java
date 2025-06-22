/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sorting.customer.orders;

/**
 *
 * @author dell
 */
import java.util.*;

public class Order {

    static class CustomerOrder {
        int orderId;
        String customerName;
        double totalPrice;

        public CustomerOrder(int orderId, String customerName, double totalPrice) {
            this.orderId = orderId;
            this.customerName = customerName;
            this.totalPrice = totalPrice;
        }

        public void display() {
            System.out.println("Order ID: " + orderId + ", Name: " + customerName + ", Price: Rs " + totalPrice);
        }
    }

    public static void bubbleSort(CustomerOrder[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].totalPrice > orders[j + 1].totalPrice) {
                    CustomerOrder temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; 
        }
    }

    public static void quickSort(CustomerOrder[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    public static int partition(CustomerOrder[] orders, int low, int high) {
        double pivot = orders[high].totalPrice;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (orders[j].totalPrice <= pivot) {
                i++;
                CustomerOrder temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        CustomerOrder temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of customer orders: ");
        int n = sc.nextInt();
        sc.nextLine();

        CustomerOrder[] orders = new CustomerOrder[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for Order " + (i + 1));
            System.out.print("Order ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Customer Name: ");
            String name = sc.nextLine();
            System.out.print("Total Price: ");
            double price = sc.nextDouble();
            sc.nextLine();
            orders[i] = new CustomerOrder(id, name, price);
        }

        CustomerOrder[] bubbleOrders = Arrays.copyOf(orders, n);
        CustomerOrder[] quickOrders = Arrays.copyOf(orders, n);

        long startBubble = System.nanoTime();
        bubbleSort(bubbleOrders);
        long endBubble = System.nanoTime();

        long startQuick = System.nanoTime();
        quickSort(quickOrders, 0, n - 1);
        long endQuick = System.nanoTime();

        System.out.println("\nBubble Sort Result:");
        for (CustomerOrder o : bubbleOrders) o.display();

        System.out.println("\nQuick Sort Result:");
        for (CustomerOrder o : quickOrders) o.display();

        // Time comparison
        long bubbleTime = endBubble - startBubble;
        long quickTime = endQuick - startQuick;

        System.out.println("\nTime taken by Bubble Sort: " + bubbleTime + " ns");
        System.out.println("Time taken by Quick Sort: " + quickTime + " ns");

        System.out.println("\nConclusion:");
        if (quickTime < bubbleTime) {
            System.out.println("Quick Sort is faster and preferred for larger datasets.");
        } else if (bubbleTime < quickTime) {
            System.out.println("Bubble Sort was faster here (likely due to small size), but not scalable.");
        } else {
            System.out.println("Both were equally fast here.");
        }

        sc.close();
    }
}
