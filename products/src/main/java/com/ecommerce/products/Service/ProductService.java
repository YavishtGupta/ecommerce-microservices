package com.ecommerce.products.Service;

import com.ecommerce.products.Model.Product;
import java.util.ArrayList;

public interface ProductService {
    ArrayList<Product> displayAll();
    Product findById(long id);
    String addProduct(Product product);
    String updateProduct(long id, Product updatedProduct);
    String deleteAll();
    String deleteById(long id);
}
