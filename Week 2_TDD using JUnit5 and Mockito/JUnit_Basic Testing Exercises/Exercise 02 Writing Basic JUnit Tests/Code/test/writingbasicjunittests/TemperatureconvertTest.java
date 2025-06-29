/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package writingbasicjunittests;

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
public class TemperatureconvertTest {

    @Test
    public void testCelsiusToFahrenheit() {
        Temperatureconvert converter = new Temperatureconvert();
        double result = converter.celsiusToFahrenheit(0);
        assertEquals(32.0, result, 0.001);
    }

    @Test
    public void testFahrenheitToCelsius() {
        Temperatureconvert converter = new Temperatureconvert();
        double result = converter.fahrenheitToCelsius(212);
        assertEquals(100.0, result, 0.001);
    }

    @Test
    public void testPrintSuccessMessage() {
        System.out.println("Testing is successful and completed.");
    }
}
