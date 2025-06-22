/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategypatternexample;

/**
 *
 * @author dell
 */
public class PaytmPayment implements PaymentStrategy {
    private String phoneNumber;

    public PaytmPayment(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid Rs " + amount + " via Paytm account: " + phoneNumber);
    }
}
