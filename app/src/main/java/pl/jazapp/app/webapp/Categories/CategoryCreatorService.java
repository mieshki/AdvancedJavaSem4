package pl.jazapp.app.webapp.Categories;

import pl.jazapp.app.webapp.Departaments.DepartmentSearchService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class CategoryCreatorService {
    @PersistenceContext
    private EntityManager em;

    @Inject
    private DepartmentSearchService departmentSearch;

    @Transactional
    public void addCategory(CategoriesRequest req) {
        var category = new CategoryEntity();
        category.setName(req.getName());
        category.setDepartment_id(departmentSearch.findDepartment(req.getDepartment_id()).get());

        em.persist(category);
    }
}
