/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package assertions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dell
 */
public class AssertionsDemoTest  {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        System.out.println("All assertions ran successfully.");
    }

    @Test
    public void testAssertions() {
        assertEquals(5, 2 + 3);

        assertTrue(5 > 3);

        assertFalse(5 < 3);

        Object obj1 = null;
        assertNull(obj1);

        Object obj2 = new Object();
        assertNotNull(obj2);
    }
}