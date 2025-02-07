package com.shashi.esdemo.service;

import com.shashi.esdemo.entity.Product;
import com.shashi.esdemo.entity.ProductIndex;
import com.shashi.esdemo.repo.ProductIndexRepository;
import com.shashi.esdemo.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductIndexRepository productIndexRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    // Save product to MySQL and index it in Elasticsearch
    public Product saveProduct(Product product) {
        Product savedProduct = productRepository.save(product); // Save to MySQL

        // Index the product in Elasticsearch
        productIndexRepository.save(toProductIndex(savedProduct));

        return savedProduct;
    }

    public List<Product> saveAll(List<Product> products) {
        List<Product> productList = productRepository.saveAll(products);
        List<ProductIndex> pi = productList.stream().map(this::toProductIndex).toList();
        elasticsearchTemplate.save(pi);
        return productList;
    }

    // Search products in Elasticsearch
    public List<ProductIndex> searchProducts(String query) {
        return productIndexRepository.findByName(query);
    }

    // Get products by category from MySQL
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    private ProductIndex toProductIndex(Product product) {
        ProductIndex productIndex = new ProductIndex();
        productIndex.setId(product.getId().toString());
        productIndex.setName(product.getName());
        productIndex.setDescription(product.getDescription());
        productIndex.setPrice(product.getPrice());
        productIndex.setCategory(product.getCategory());
        return productIndex;
    }
}