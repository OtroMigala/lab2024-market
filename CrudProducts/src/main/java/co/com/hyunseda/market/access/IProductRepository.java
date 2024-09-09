package co.com.hyunseda.market.access;

import java.util.List;

import co.com.hyunseda.market.service.Product;

public interface IProductRepository {
    boolean save(Product product);
    List<Product> findAll();
    Product findById(Long id);
    boolean edit(Long id, Product product);
    boolean delete(Long id);
    List<Product> findByName(String name);
    List<Product> findByCategory(Long categoryId);
    
}