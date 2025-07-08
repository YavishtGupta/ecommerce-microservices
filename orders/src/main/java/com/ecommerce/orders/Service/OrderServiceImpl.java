package com.ecommerce.orders.Service;
import com.ecommerce.orders.Dto.Customer;
import com.ecommerce.orders.Dto.OrderRequest;
import com.ecommerce.orders.Dto.OrderResponse;
import com.ecommerce.orders.Dto.Product;
import com.ecommerce.orders.Model.Order;
import com.ecommerce.orders.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String CUSTOMER_SERVICE_URL = "http://localhost:8081/customer/view/";
    private final String PRODUCT_SERVICE_URL = "http://localhost:8082/products/view/";

    @Override
    public String placeOrder(OrderRequest request){

        Customer customer;
        try {
            customer = restTemplate.getForObject(CUSTOMER_SERVICE_URL + request.getCustomer_id(), Customer.class);
            if (customer == null) {
                throw new RuntimeException("Customer not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Customer not found: " + request.getCustomer_id());
        }

        List<Product> products = request.getProduct_id().stream()
                .map(id -> {
                    try {
                        Product product = restTemplate.getForObject(PRODUCT_SERVICE_URL + id, Product.class);
                        if (product == null) {
                            throw new RuntimeException("Product not found: " + id);
                        }
                        return product;
                    } catch (Exception e) {
                        throw new RuntimeException("Product not found: " + id);
                    }
                })
                .toList();


        Order order = new Order();
        order.setCustomer_id(request.getCustomer_id());
        order.setProduct_id(request.getProduct_id());
        orderRepository.save(order);

        return "Order placed!";
    }

    @Override
    public List<OrderResponse> getAllOrders(){
        return orderRepository.findAll().stream()
                .map(this::buildOrderResponse)
                .toList();
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(this::buildOrderResponse)
                .orElse(null);
    }

    private OrderResponse buildOrderResponse(Order order) {
        // Fetch customer
        Customer customer = restTemplate.getForObject(CUSTOMER_SERVICE_URL + order.getCustomer_id(), Customer.class);

        // Fetch products using streams
        List<Product> products = order.getProduct_id().stream()
                .map(productId -> restTemplate.getForObject(PRODUCT_SERVICE_URL + productId, Product.class))
                .collect(Collectors.toList());

        return new OrderResponse(order.getId(), customer, products);
    }
}
