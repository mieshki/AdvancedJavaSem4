package pl.jazapp.app.Categories;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CategorySearchService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Optional<CategoryEntity> findCategoryById(Long id)
    {
        return em.createQuery("from CategoryEntity where id = :id", CategoryEntity.class)
                .setParameter("id", id)
                .getResultList().stream()
                .findFirst();
    }

    public List<CategoryEntity> getAllCategories()
    {
        return em.createNamedQuery("Category.findAll", CategoryEntity.class).getResultList();
    }

    public List<CategoryEntity> getAllCategoriesOfSpecifiedDepartment(Long department_id)
    {
        return em.createQuery("from CategoryEntity where department = :id", CategoryEntity.class)
                .setParameter("id", department_id)
                .getResultList();
    }


}
