    package co.com.hyunseda.market.access;

    import java.util.List;

    import co.com.hyunseda.market.service.Category;

    public interface ICategoryRepository {
        boolean save(Category category);
        List<Category> findAll();
        Category findById(Long id);
        boolean edit(Long id, Category category);
        boolean delete(Long id);
    }