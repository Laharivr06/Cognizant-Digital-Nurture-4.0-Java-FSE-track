/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observerpatternexample;

/**
 *
 * @author dell
 */
public class Test {
    public static void main(String[] args) {
        StockMarket tataStock = new StockMarket("TATA", 100.00);

        Observer user1 = new MobileApp("X");
        Observer user2 = new WebApp("Y");

        tataStock.registerObserver(user1);
        tataStock.registerObserver(user2);

        tataStock.setPrice(1000.00);

        tataStock.removeObserver(user2);

        tataStock.setPrice(2000.0);
    }
}