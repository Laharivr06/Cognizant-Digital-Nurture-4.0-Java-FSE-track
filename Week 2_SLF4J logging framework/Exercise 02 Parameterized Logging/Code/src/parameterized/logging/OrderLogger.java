/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parameterized.logging;

/**
 *
 * @author dell
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OrderLogger {

    private static final Logger logger = LoggerFactory.getLogger(OrderLogger.class);

    public static void main(String[] args) {
        String item = "Book";
        int quantity = 4;
        double unitPrice = 100.0;

        logger.info("Order placed: {}, Quantity: {}, Total Amount: ₹{}", item, quantity, quantity * unitPrice);
    }
}