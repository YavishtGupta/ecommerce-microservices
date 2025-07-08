package com.ecommerce.customer.Service;
import com.ecommerce.customer.Model.Customer;
import com.ecommerce.customer.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public ArrayList<Customer> displayAll(){
        return (ArrayList<Customer>) customerRepository.findAll();
    }

    @Override
    public Customer findbyid(long id){
        return customerRepository.findById(id).orElse(null);
    }


    public String addcustomer(Customer customer){
        customerRepository.save(customer);
        return "Customer Added";
    }
    public String updatecustomer(long id, Customer updatedcustomer) {
        return customerRepository.findById(id).
                map(existingCustomer -> {
                            existingCustomer.setAddress(updatedcustomer.getAddress());
                            existingCustomer.setName(updatedcustomer.getName());
                            customerRepository.save(existingCustomer);
                            return "Customer Updated";
                        }
                ).orElse( "Customer not found");
    }

    public String deleteall(){
        customerRepository.deleteAll();
        return  "All customers deleted.";
    }

    public String deletebyid(long id) {
        return customerRepository.findById(id).map(
                customer -> {
                    customerRepository.delete(customer);
                    return "Customer deleted";
                }
        ).orElse("Customer not found");
    }
}
