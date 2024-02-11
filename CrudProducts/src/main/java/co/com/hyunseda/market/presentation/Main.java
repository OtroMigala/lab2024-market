
package co.com.hyunseda.market.presentation;

import co.com.hyunseda.market.service.ProductService;

/**
 *
 * @author Libardo Pantoja
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ProductService productService = new ProductService();
        GUIProducts instance = new GUIProducts(productService);
        instance.setVisible(true);
    }
    
}
