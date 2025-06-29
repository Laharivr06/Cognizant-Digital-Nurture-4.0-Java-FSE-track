/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package arrange.act.pkgassert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dell
 */
public class StudentTest {

    private Student student;

    @Before
    public void setUp() {
        student = new Student("Alice", 88); // Common test fixture
    }

    @After
    public void tearDown() {
        student = null;
        System.out.println("Test case completed and cleaned.");
    }

    @Test
    public void testHasPassed() {

        boolean result = student.hasPassed();

        assertTrue(result);
    }

    @Test
    public void testGetGrade() {
        String grade = student.getGrade();

        assertEquals("B", grade);
    }

    @Test
    public void testFailingStudent() {
        student = new Student("Bob", 30);

        boolean result = student.hasPassed();
        String grade = student.getGrade();

        assertFalse(result);
        assertEquals("F", grade);
    }
}