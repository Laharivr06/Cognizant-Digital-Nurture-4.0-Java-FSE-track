/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arrange.act.pkgassert;

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

    public String getGrade() {
        if (marks >= 90) return "A";
        if (marks >= 75) return "B";
        if (marks >= 60) return "C";
        if (marks >= 35) return "D";
        return "F";
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }
}