/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package library.management.system;

/**
 *
 * @author dell
 */
import java.util.*;

public class Book {
    static class BookRecord {
        int bookId;
        String title;
        String author;

        BookRecord(int bookId, String title, String author) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
        }

        void display() {
            System.out.println("Book ID: " + bookId + ", Title: " + title + ", Author: " + author);
        }
    }

    static ArrayList<BookRecord> books = new ArrayList<>();

    public static long linearSearch(int bookId) {
        boolean found = false;
        long start = System.nanoTime();

        for (BookRecord b : books) {
            if (b.bookId == bookId) {
                b.display();
                found = true;
            }
        }

        long end = System.nanoTime();
        if (!found) System.out.println("Book not found (Linear Search).");
        System.out.println("Time taken (Linear Search): " + (end - start) + " ns ");
        return end - start;
    }

    public static long binarySearch(int bookId) {
        books.sort(Comparator.comparingInt(b -> b.bookId)); 

        int left = 0, right = books.size() - 1;
        boolean found = false;

        long start = System.nanoTime();
        while (left <= right) {
            int mid = (left + right) / 2;
            int midId = books.get(mid).bookId;

            if (midId == bookId) {
                books.get(mid).display();
                found = true;
                break;
            } else if (bookId < midId) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        long end = System.nanoTime();

        if (!found) System.out.println("Book not found (Binary Search).");
        System.out.println("Time taken (Binary Search): " + (end - start) + " ns ");
        return end - start;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n---Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book by ID (Compare Linear & Binary Search)");
            System.out.println("3. View All Books");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();

                    books.add(new BookRecord(id, title, author));
                    System.out.println("Book added.");
                }

                case 2 -> {
                    System.out.print("Enter Book ID to Search: ");
                    int id = sc.nextInt();

                    long linearTime = linearSearch(id);
                    long binaryTime = binarySearch(id);

                    System.out.println("\nTime Comparison:");
                    System.out.println("→ Linear Search Time: " + linearTime + " ns");
                    System.out.println("→ Binary Search Time: " + binaryTime + " ns");

                    if (linearTime < binaryTime) {
                        System.out.println("Linear Search was faster.");
                    } else if (binaryTime < linearTime) {
                        System.out.println("Binary Search was faster.");
                    } else {
                        System.out.println("Both performed similarly.");
                    }
                }

                case 3 -> {
                    System.out.println("Book List:");
                    if (books.isEmpty()) {
                        System.out.println("Library is empty.");
                    } else {
                        for (BookRecord b : books) b.display();
                    }
                }

                case 4 -> System.out.println("Exiting.");

                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 4);

        sc.close();
    }
}