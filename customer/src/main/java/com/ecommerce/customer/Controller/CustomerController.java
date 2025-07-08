package com.ecommerce.customer.Controller;
import com.ecommerce.customer.Model.Customer;
import com.ecommerce.customer.Service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RequestMapping("/customer")
@RestController
public class CustomerController {
    @Autowired
    CustomerServiceImpl customerService;

    @GetMapping("/view")
    public ArrayList<Customer> getAllCustomers() {
        return customerService.displayAll();
    }

    @GetMapping("/view/{id}")
    public Customer getCustomerById(@PathVariable long id) {
        return customerService.findbyid(id);
    }

    @PostMapping("/add")
    public String addCustomer(@RequestBody Customer customer) {
        return customerService.addcustomer(customer);
    }

    @PutMapping("/update/{id}")
    public String updateCustomer(@PathVariable long id, @RequestBody Customer updatedCustomer) {
        return customerService.updatecustomer(id, updatedCustomer);
    }

    @DeleteMapping("/deleteall")
    public String deleteAllCustomers() {
        return customerService.deleteall();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomerById(@PathVariable long id) {
        return customerService.deletebyid(id);
    }

}
