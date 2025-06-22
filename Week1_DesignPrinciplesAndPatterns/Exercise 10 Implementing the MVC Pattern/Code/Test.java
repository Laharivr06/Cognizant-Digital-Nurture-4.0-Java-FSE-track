/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvcpatternexample;

/**
 *
 * @author dell
 */
public class Test {
    public static void main(String[] args) {
        Student student = new Student();
        student.setName("ABC");
        student.setId("1");
        student.setGrade("A");

        StudentView view = new StudentView();

        StudentController controller = new StudentController(student, view);

        controller.updateView();

        controller.setStudentName("XYZ");
        controller.setStudentId("2");
        controller.setStudentGrade("A+");

        controller.updateView();
    }
}
