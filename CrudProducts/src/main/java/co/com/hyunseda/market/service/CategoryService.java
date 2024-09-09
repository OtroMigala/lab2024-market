package co.com.hyunseda.market.service;

import java.util.List;

import co.com.hyunseda.market.access.ICategoryRepository;

public class CategoryService {
    private ICategoryRepository repository;

    public CategoryService(ICategoryRepository repository) {
        this.repository = repository;
    }

    public boolean saveCategory(String name) {
        Category newCategory = new Category();
        newCategory.setName(name);
        
        return repository.save(newCategory);
    }

    public List<Category> findAllCategories() {
        return repository.findAll();
    }
    
    public Category findCategoryById(Long id) {
        return repository.findById(id);
    }
    
    public boolean deleteCategory(Long id) {
        return repository.delete(id);
    }

    public boolean editCategory(Long categoryId, Category category) {
        return repository.edit(categoryId, category);
    }
}