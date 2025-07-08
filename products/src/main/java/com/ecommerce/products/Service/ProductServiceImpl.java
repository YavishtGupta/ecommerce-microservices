package com.ecommerce.products.Service;

import com.ecommerce.products.Model.Product;
import com.ecommerce.products.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public ArrayList<Product> displayAll() {
        return (ArrayList<Product>) productRepository.findAll();
    }

    @Override
    public Product findById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public String addProduct(Product product) {
        productRepository.save(product);
        return "Product Added";
    }

    @Override
    public String updateProduct(long id, Product updatedProduct) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(updatedProduct.getName());
                    existingProduct.setDescription(updatedProduct.getDescription());
                    productRepository.save(existingProduct);
                    return "Product Updated";
                })
                .orElse("Product not found");
    }

    @Override
    public String deleteAll() {
        productRepository.deleteAll();
        return "All products deleted.";
    }

    @Override
    public String deleteById(long id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return "Product deleted";
                })
                .orElse("Product not found");
    }
}
