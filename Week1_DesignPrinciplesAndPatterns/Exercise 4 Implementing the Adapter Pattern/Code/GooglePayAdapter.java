/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapterpatternexample;

/**
 *
 * @author dell
 */
public class GooglePayAdapter implements PaymentProcessor {
    private GooglePayGateway gpay;

    public GooglePayAdapter(GooglePayGateway gpay) {
        this.gpay = gpay;
    }

    @Override
    public void processPayment(double amount) {
        gpay.sendMoney(amount);
    }
}