/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package strategypatternexample;

/**
 *
 * @author dell
 */
public class Test {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456"));
        context.pay(1000.00);

        context.setPaymentStrategy(new PaytmPayment("+91 9123456780"));
        context.pay(2000.00);

        context.setPaymentStrategy(new GooglePayPayment("abcabc@hdfc"));
        context.pay(100.00);
    }
}
