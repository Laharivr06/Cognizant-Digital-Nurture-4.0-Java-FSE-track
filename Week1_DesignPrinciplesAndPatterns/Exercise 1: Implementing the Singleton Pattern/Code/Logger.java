/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singletonpatternexample;

public class Logger {
    private static final Logger instance = new Logger();

    private Logger() {
        System.out.println("Logger initialized");
    }

    public static Logger getInstance() {
        return instance;
    }

    public void log(String message) {
        System.out.println("LOG: " + message);
    }
}