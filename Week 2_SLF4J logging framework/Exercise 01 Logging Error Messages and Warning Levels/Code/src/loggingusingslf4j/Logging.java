/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package loggingusingslf4j;

/**
 *
 * @author dell
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logging  {
    private static final Logger logger = LoggerFactory.getLogger(Logging.class);

    public static void main(String[] args) {
        logger.error("This is an error message");
        logger.warn("This is a warning message");
    }
}