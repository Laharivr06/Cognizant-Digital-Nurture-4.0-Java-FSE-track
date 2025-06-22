/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcpatternexample;

/**
 *
 * @author dell
 */
public class StudentView {
    public void displayStudentDetails(String name, String id, String grade) {
        System.out.println("\n--- Student Details ---");
        System.out.println("Name  : " + name);
        System.out.println("ID    : " + id);
        System.out.println("Grade : " + grade);
    }
}