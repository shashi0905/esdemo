package com.shashi.esdemo.service;

import com.shashi.esdemo.entity.Product;
import com.shashi.esdemo.entity.ProductIndex;
import com.shashi.esdemo.repo.ProductIndexRepository;
import com.shashi.esdemo.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductIndexRepository productIndexRepository;

    // Save product to MySQL and index it in Elasticsearch
    public Product saveProduct(Product product) {
        Product savedProduct = productRepository.save(product); // Save to MySQL

        // Index the product in Elasticsearch
        ProductIndex productIndex = new ProductIndex();
        productIndex.setId(savedProduct.getId().toString());
        productIndex.setName(savedProduct.getName());
        productIndex.setDescription(savedProduct.getDescription());
        productIndex.setPrice(savedProduct.getPrice());
        productIndex.setCategory(savedProduct.getCategory());
        productIndexRepository.save(productIndex);

        return savedProduct;
    }

    // Search products in Elasticsearch
    public List<ProductIndex> searchProducts(String query) {
        return productIndexRepository.findByName(query);
    }

    // Get products by category from MySQL
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }
}