/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dependencyinjectionexample;

/**
 *
 * @author dell
 */
import java.util.HashMap;
import java.util.Map;

public class CustomerRepositoryImpl implements CustomerRepository {
    private Map<String, Customer> customerDB = new HashMap<>();

    public CustomerRepositoryImpl() {
        // Simulated DB records
        customerDB.put("1", new Customer("1", "ABC"));
        customerDB.put("2", new Customer("2", "XYZ"));
    }

    @Override
    public Customer findCustomerById(String id) {
        return customerDB.get(id);
    }
}
