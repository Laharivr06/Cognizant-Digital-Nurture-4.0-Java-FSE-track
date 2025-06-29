/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package settingupjunit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author dell
 */
public class StudentTest {

    @Test
    public void testHasPassed() {
        Student student = new Student("John", 50);
        assertTrue(student.hasPassed());

        System.out.println("Testing is successful and completed.");
    }
}