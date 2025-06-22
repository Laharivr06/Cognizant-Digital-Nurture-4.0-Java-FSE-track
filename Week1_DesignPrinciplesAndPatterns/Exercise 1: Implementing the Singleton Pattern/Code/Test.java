/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singletonpatternexample;

/**
 *
 * @author dell
 */
public class Test {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("Starting the application");
        logger2.log("Application is running");

        if (logger1 == logger2) {
            System.out.println("Same logger instance used. Singleton Successful");
        } else {
            System.out.println("Different instances - Singleton failed");
        }
    }
}