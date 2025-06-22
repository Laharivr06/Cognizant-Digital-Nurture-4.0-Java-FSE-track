/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adapterpatternexample;

/**
 *
 * @author dell
 */
public class PaytmAdapter implements PaymentProcessor {
    private PaytmGateway paytm;

    public PaytmAdapter(PaytmGateway paytm) {
        this.paytm = paytm;
    }

    @Override
    public void processPayment(double amount) {
        paytm.payViaPaytm(amount);
    }
}
