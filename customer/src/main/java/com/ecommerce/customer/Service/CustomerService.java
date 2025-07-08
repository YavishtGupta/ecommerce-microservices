package com.ecommerce.customer.Service;
import com.ecommerce.customer.Model.Customer;
import java.util.ArrayList;

public interface CustomerService {
    ArrayList<Customer> displayAll();
    Customer findbyid(long id);
    String addcustomer(Customer customer);
    String updatecustomer(long id, Customer updatedcustomer);
    String deleteall();
    String deletebyid(long id);
}
