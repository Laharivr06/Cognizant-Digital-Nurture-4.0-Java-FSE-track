/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package builderpatternexample;

/**
 *
 * @author dell
 */
public class Test {
    public static void main(String[] args) {
        Computer myPC = new Computer.Builder()
                            .setCPU("Intel Core i7")
                            .setRAM("16GB")
                            .setStorage("512GB SSD")
                            .build();

        System.out.println("My PC: " + myPC);
    }
}