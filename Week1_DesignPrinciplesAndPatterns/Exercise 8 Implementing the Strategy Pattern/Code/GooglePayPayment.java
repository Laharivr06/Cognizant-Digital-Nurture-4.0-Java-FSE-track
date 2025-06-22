/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategypatternexample;

/**
 *
 * @author dell
 */
public class GooglePayPayment implements PaymentStrategy {
    private String gpayId;

    public GooglePayPayment(String gpayId) {
        this.gpayId = gpayId;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid Rs " + amount + " using Google Pay ID: " + gpayId);
    }
}
