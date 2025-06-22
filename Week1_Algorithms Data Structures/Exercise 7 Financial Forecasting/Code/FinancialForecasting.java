/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package financial.forecasting;

/**
 *
 * @author dell
 */
import java.util.*;

public class FinancialForecasting {

    public static double forecastRecursive(double initialValue, double growthRate, int years) {
        if (years == 0) return initialValue;
        return (1 + growthRate) * forecastRecursive(initialValue, growthRate, years - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nFinancial Forecasting Tool");
        System.out.print("Enter Initial Value: ");
        double initialValue = sc.nextDouble();

        System.out.print("Enter Annual Growth Rate: ");
        double growthRate = sc.nextDouble();

        System.out.print("Enter Number of Years: ");
        int years = sc.nextInt();

        long start = System.nanoTime();
        double forecast = forecastRecursive(initialValue, growthRate, years);
        long end = System.nanoTime();

        System.out.printf("\nRecursive Forecast Value: %.2f\n", forecast);
        System.out.println("Time taken (Recursive): " + (end - start) + " ns ");

        sc.close();
    }
}
