/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapterpatternexample;

/**
 *
 * @author dell
 */
public class GooglePayGateway {
    public void sendMoney(double amountInINR) {
        System.out.println("Payment of Rs " + amountInINR + " done using Google Pay.");
    }
}