/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factorymethodpatternexample;

/**
 *
 * @author dell
 */
public class PdfDocument implements Document {
    @Override
    public void open() {
        System.out.println("PDF document opening");
    }
}
