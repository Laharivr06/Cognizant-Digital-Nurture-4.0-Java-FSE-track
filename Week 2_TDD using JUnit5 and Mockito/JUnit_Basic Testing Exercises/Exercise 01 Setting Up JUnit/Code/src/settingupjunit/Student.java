/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package settingupjunit;

/**
 *
 * @author dell
 */
public class Student {
    private String name;
    private int marks;

    public Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    public boolean hasPassed() {
        return marks >= 35;
    }

    public String getName() {
        return name;
    }
}