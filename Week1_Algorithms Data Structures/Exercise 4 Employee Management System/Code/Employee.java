/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package employee.management.system;

/**
 *
 * @author dell
 */
import java.util.Scanner;

public class Employee  {
    static class EmployeeRecord {
        int employeeId;
        String name;
        String position;
        double salary;

        public EmployeeRecord(int employeeId, String name, String position, double salary) {
            this.employeeId = employeeId;
            this.name = name;
            this.position = position;
            this.salary = salary;
        }

        public void display() {
            System.out.println("ID: " + employeeId + ", Name: " + name + ", Position: " + position + ", Salary: Rs " + salary);
        }
    }

    static EmployeeRecord[] employees;
    static int count = 0;
    
    public static void addEmployee(EmployeeRecord e) {
        if (count < employees.length) {
            employees[count++] = e;
            System.out.println("Employee added successfully.");
        } else {
            System.out.println("Cannot add more employees.");
        }
    }

    public static int searchEmployee(int id) {
        for (int i = 0; i < count; i++) {
            if (employees[i].employeeId == id) {
                return i;
            }
        }
        return -1;
    }

    public static void displayAll() {
        if (count == 0) {
            System.out.println("No employees found.");
            return;
        }
        for (int i = 0; i < count; i++) {
            employees[i].display();
        }
    }

    public static void deleteEmployee(int id) {
        int index = searchEmployee(id); 
        if (index == -1) {
            System.out.println("Employee not found.");
            return;
        }

        for (int i = index; i < count - 1; i++) {
            employees[i] = employees[i + 1]; 
        }
        employees[--count] = null;
        System.out.println("Employee deleted successfully.");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter max number of employees: ");
        int size = sc.nextInt();
        employees = new EmployeeRecord[size];

        int choice;
        do {
            System.out.println("\n--- Employee Management ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Search Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. View All Employees");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Position: ");
                    String position = sc.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = sc.nextDouble();
                    sc.nextLine();

                    long start = System.nanoTime();
                    addEmployee(new EmployeeRecord(id, name, position, salary));
                    long end = System.nanoTime();
                    System.out.println("Time taken to add: " + (end - start) + " ns ");
                }

                case 2 -> {
                    System.out.print("Enter ID to search: ");
                    int id = sc.nextInt();
                    long start = System.nanoTime();
                    int idx = searchEmployee(id);
                    long end = System.nanoTime();
                    if (idx != -1) {
                        employees[idx].display();
                    } else {
                        System.out.println("Employee not found.");
                    }
                    System.out.println("Time taken to search: " + (end - start) + " ns ");
                }

                case 3 -> {
                    System.out.print("Enter ID to delete: ");
                    int id = sc.nextInt();
                    long start = System.nanoTime();
                    deleteEmployee(id);
                    long end = System.nanoTime();
                    System.out.println("Time taken to delete: " + (end - start) + " ns ");
                }

                case 4 -> {
                    long start = System.nanoTime();
                    displayAll();
                    long end = System.nanoTime();
                    System.out.println("Time taken to traverse: " + (end - start) + " ns ");
                }

                case 5 -> System.out.println("Exiting system.");

                default -> System.out.println("Invalid option.");
            }

        } while (choice != 5);
        sc.close();
    }
}