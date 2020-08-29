package pl.jazapp.app.webapp.Categories;

import pl.jazapp.app.Categories.CategoryCreatorService;
import pl.jazapp.app.Categories.CategoryEditService;
import pl.jazapp.app.Categories.CategoryEntity;
import pl.jazapp.app.Categories.CategorySearchService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class CategoriesManager {
    @Inject
    CategoryCreatorService categoryCreator;
    @Inject
    CategorySearchService categorySearch;
    @Inject
    CategoryEditService categoryEdit;

    public String addCategory(CategoriesRequest req)
    {
        if(categorySearch.findCategoryById(req.getId()).isEmpty() || req.getId() == 0)
        {
            categoryCreator.addCategory(req);
        } else {
            categoryEdit.editCategory(req);
        }

        return "/categories/list.xhtml?faces-redirect=true";
    }

    public String findCategoryByIdAndReturnItsName(Long id)
    {
        if(id == null)
            return "";

        var category = categorySearch.findCategoryById(id);
        if(category.isPresent())
            return category.get().getName();
        else
            return "";
    }

    public List<CategoryEntity> getAllCategories()
    {
        return categorySearch.getAllCategories();
    }

    public List<CategoryEntity> getAllCategoriesOfSpecifiedDepartment(Long department_id)
    {
        return categorySearch.getAllCategoriesOfSpecifiedDepartment(department_id);
    }
}
