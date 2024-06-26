package pl.jazapp.app.Categories;

import pl.jazapp.app.webapp.Categories.CategoriesRequest;
import pl.jazapp.app.Departments.DepartmentSearchService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class CategoryEditService {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private DepartmentSearchService departmentSearch;

    @Transactional
    public void editCategory(CategoriesRequest req) {
        var category = new CategoryEntity();
        category.setId(req.getId());
        category.setName(req.getName());
        category.setDepartment_id(departmentSearch.findDepartment(req.getDepartment_id()).get());

        em.merge(category);
    }
}
