package com.shashi.esdemo.repo;

import com.shashi.esdemo.entity.ProductIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;

public interface ProductIndexRepository extends ElasticsearchRepository<ProductIndex, String> {
    List<ProductIndex> findByName(String name);
    List<ProductIndex> findByCategory(String category);
    List<ProductIndex> findByPriceBetween(double minPrice, double maxPrice);
}
