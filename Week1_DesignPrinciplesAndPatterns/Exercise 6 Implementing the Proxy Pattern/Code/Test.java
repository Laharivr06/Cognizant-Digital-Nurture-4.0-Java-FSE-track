/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proxypatternexample;

/**
 *
 * @author dell
 */
public class Test {
    public static void main(String[] args) {
        Image img1 = new ProxyImage("image1.jpg");

        img1.display();

        img1.display();

        Image img2 = new ProxyImage("image2.png");
        img2.display();
    }
}