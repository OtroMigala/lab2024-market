package co.com.hyunseda.market.service;

import java.util.List;

import co.com.hyunseda.market.access.IProductRepository;

public class ProductService {
    private IProductRepository repository;

    public ProductService(IProductRepository repository) {
        this.repository = repository;
    }

    public boolean saveProduct(String name, String description, Long categoryId) {
        Product newProduct = new Product();
        newProduct.setName(name);
        newProduct.setDescription(description);
        newProduct.setCategoryId(categoryId);
        
        if (newProduct.getName().isEmpty()) {
            return false;
        }
    
        return repository.save(newProduct);
    }

    public List<Product> findAllProducts() {
        return repository.findAll();
    }
    
    public Product findProductById(Long id) {
        return repository.findById(id);
    }
    
    public boolean deleteProduct(Long id) {
        return repository.delete(id);
    }

    public boolean editProduct(Long productId, Product prod) {
        //Validate product
        if (prod == null || prod.getName().isEmpty()) {
            return false;
        }
        return repository.edit(productId, prod);
    }

    public List<Product> findProductsByName(String name) {
        return repository.findByName(name);
    }

    public List<Product> findProductsByCategory(Long categoryId) {
        return repository.findByCategory(categoryId);
    }
}