package com.ecommerce.customer.Repository;
import com.ecommerce.customer.Model.customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<customer, Long> {
    
}