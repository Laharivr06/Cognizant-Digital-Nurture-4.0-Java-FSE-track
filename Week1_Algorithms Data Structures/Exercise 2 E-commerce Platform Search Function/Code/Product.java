/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package e.commerce.platform.search;

/**
 *
 * @author dell
 */
import java.util.*;
 
public class Product  {
    int productId;
    String productName;
    String category;

    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public void display() {
        System.out.println("ID: " + productId + ", Name: " + productName + ", Category: " + category);
    }
}

class EcommerceSearch {

    static ArrayList<Product> products = new ArrayList<>();

    public static int linearSearch(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).productId == id) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(List<Product> sortedProducts, int id) {
        int low = 0, high = sortedProducts.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (sortedProducts.get(mid).productId == id) {
                return mid;
            } else if (id < sortedProducts.get(mid).productId) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static List<Product> getSortedProducts() {
        List<Product> sorted = new ArrayList<>(products);
        sorted.sort(Comparator.comparingInt(p -> p.productId));
        return sorted;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of products: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Enter details for Product " + (i + 1));
            System.out.print("Product ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Product Name: ");
            String name = sc.nextLine();
            System.out.print("Category: ");
            String category = sc.nextLine();
            products.add(new Product(id, name, category));
        }

        System.out.print("\nEnter Product ID to search: ");
        int searchId = sc.nextInt();

        long linearStart = System.nanoTime();
        int linearIndex = linearSearch(searchId);
        long linearEnd = System.nanoTime();

        System.out.println("\nUsing Linear Search:");
        if (linearIndex != -1)
            products.get(linearIndex).display();
        else
            System.out.println("Product not found.");

        long linearTime = linearEnd - linearStart;
        System.out.println("Time taken: " + linearTime + " ns");

        List<Product> sortedProducts = getSortedProducts();

        long binaryStart = System.nanoTime();
        int binaryIndex = binarySearch(sortedProducts, searchId);
        long binaryEnd = System.nanoTime();

        System.out.println("\nUsing Binary Search (on sorted data):");
        if (binaryIndex != -1)
            sortedProducts.get(binaryIndex).display();
        else
            System.out.println("Product not found.");

        long binaryTime = binaryEnd - binaryStart;
        System.out.println("Time taken: " + binaryTime + " ns");

        System.out.println("\nTime Complexity:");
        System.out.println("Linear Search: O(n)");
        System.out.println("Binary Search: O(log n) requires sorted data");

        System.out.println("\nPerformance Summary:");
        if (binaryTime < linearTime) {
            System.out.println("Binary Search was faster in this case.");
        } else if (linearTime < binaryTime) {
            System.out.println("Linear Search was faster in this case (possible on very small data).");
        } else {
            System.out.println("Both searches took approximately the same time.");
        }

        //System.out.println("\nConclusion: For large and sorted datasets, Binary Search is more efficient.");
        sc.close();
    }
}
