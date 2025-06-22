/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapterpatternexample;

/**
 *
 * @author dell
 */
public class Test {
    public static void main(String[] args) {
        PaymentProcessor paytm = new PaytmAdapter(new PaytmGateway());
        paytm.processPayment(1000.0);

        PaymentProcessor gpay = new GooglePayAdapter(new GooglePayGateway());
        gpay.processPayment(2000.0);
    }
}
