package com.shashi.esdemo.controller;

import com.shashi.esdemo.entity.Product;
import com.shashi.esdemo.entity.ProductIndex;
import com.shashi.esdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PostMapping("/bulk")
    public List<Product> addBulkProduct(@RequestBody List<Product> products) {
        return productService.saveAll(products);
    }

    @GetMapping("/search")
    public List<ProductIndex> searchProducts(@RequestParam String query) {
        return productService.searchProducts(query);
    }

    @GetMapping("/category")
    public List<Product> getProductsByCategory(@RequestParam String category) {
        return productService.getProductsByCategory(category);
    }
}
