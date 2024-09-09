
package co.com.hyunseda.market.presentation;


import co.com.hyunseda.market.access.CategoryRepository;
import co.com.hyunseda.market.access.ICategoryRepository;
import co.com.hyunseda.market.access.IProductRepository;
import co.com.hyunseda.market.access.ProductRepository;
import co.com.hyunseda.market.service.CategoryService;
import co.com.hyunseda.market.service.ProductService;
/**
 *
 * @author Libardo Pantoja
 */
public class Main {
    public static void main(String[] args) {
        IProductRepository productRepository = new ProductRepository();
        ICategoryRepository categoryRepository = new CategoryRepository();
        
        ProductService productService = new ProductService(productRepository);
        CategoryService categoryService = new CategoryService(categoryRepository);
        
        GUIProducts instance = new GUIProducts(productService, categoryService);
        instance.setVisible(true);
    }
}
