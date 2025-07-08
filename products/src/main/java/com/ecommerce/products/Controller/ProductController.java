package com.ecommerce.products.Controller;

import com.ecommerce.products.Model.Product;
import com.ecommerce.products.Service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productService;

    @GetMapping("/view")
    public ArrayList<Product> getAllProducts() {
        return productService.displayAll();
    }

    @GetMapping("/view/{id}")
    public Product getProductById(@PathVariable long id) {
        return productService.findById(id);
    }

    @PostMapping("/add")
    public String addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable long id, @RequestBody Product updatedProduct) {
        return productService.updateProduct(id, updatedProduct);
    }

    @DeleteMapping("/deleteall")
    public String deleteAllProducts() {
        return productService.deleteAll();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProductById(@PathVariable long id) {
        return productService.deleteById(id);
    }
}
