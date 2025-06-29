/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package using.different.appenders;

/**
 *
 * @author dell
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 

public class AppLogger  {

    private static final Logger logger = LoggerFactory.getLogger(AppLogger.class);

    public static void main(String[] args) {
        logger.info("This message goes to console and app.log file.");
        logger.warn("Warning message logged.");
        logger.error("Error message logged.");
    }
}