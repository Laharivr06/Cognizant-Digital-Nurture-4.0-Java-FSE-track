/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package task.management.system;

/**
 *
 * @author dell
 */
import java.util.Scanner;

public class Task {
    static class TaskNode {
        int taskId;
        String taskName;
        String status;
        TaskNode next;

        TaskNode(int taskId, String taskName, String status) {
            this.taskId = taskId;
            this.taskName = taskName;
            this.status = status;
            this.next = null;
        }

        void display() {
            System.out.println("Task ID: " + taskId + ", Name: " + taskName + ", Status: " + status);
        }
    }

    static TaskNode head = null;

    public static void addTask(int id, String name, String status) {
        TaskNode newNode = new TaskNode(id, name, status);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode temp = head;
            while (temp.next != null) {
                temp = temp.next; 
            }
            temp.next = newNode;
        }
        System.out.println("Task added.");
    }

    public static TaskNode searchTask(int id) {
        TaskNode temp = head;
        while (temp != null) {
            if (temp.taskId == id) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    public static void traverseTasks() {
        if (head == null) {
            System.out.println("No tasks to display.");
            return;
        }
        TaskNode temp = head;
        while (temp != null) {
            temp.display();
            temp = temp.next;
        }
    }

    public static void deleteTask(int id) {
        if (head == null) {
            System.out.println("Task list is empty.");
            return;
        }

        if (head.taskId == id) {
            head = head.next;
            System.out.println("Task deleted.");
            return;
        }

        TaskNode current = head;
        while (current.next != null) {
            if (current.next.taskId == id) {
                current.next = current.next.next;
                System.out.println("Task deleted.");
                return;
            }
            current = current.next;
        }

        System.out.println("Task not found.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Task Management System ---");
            System.out.println("1. Add Task");
            System.out.println("2. Search Task");
            System.out.println("3. Delete Task");
            System.out.println("4. View All Tasks");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Task ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Task Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Status: ");
                    String status = sc.nextLine();

                    long start = System.nanoTime();
                    addTask(id, name, status);
                    long end = System.nanoTime();
                    System.out.println("Time taken to add: " + (end - start) + " ns ");
                }

                case 2 -> {
                    System.out.print("Enter Task ID to search: ");
                    int id = sc.nextInt();

                    long start = System.nanoTime();
                    TaskNode found = searchTask(id);
                    long end = System.nanoTime();

                    if (found != null) {
                        found.display();
                    } else {
                        System.out.println("Task not found.");
                    }
                    System.out.println("Time taken to search: " + (end - start) + " ns ");
                }

                case 3 -> {
                    System.out.print("Enter Task ID to delete: ");
                    int id = sc.nextInt();

                    long start = System.nanoTime();
                    deleteTask(id);
                    long end = System.nanoTime();
                    System.out.println("Time taken to delete: " + (end - start) + " ns ");
                }

                case 4 -> {
                    long start = System.nanoTime();
                    traverseTasks();
                    long end = System.nanoTime();
                    System.out.println("Time taken to traverse: " + (end - start) + " ns ");
                }

                case 5 -> System.out.println("Exiting.");

                default -> System.out.println("Invalid option.");
            }

        } while (choice != 5);

        sc.close();
    }
}