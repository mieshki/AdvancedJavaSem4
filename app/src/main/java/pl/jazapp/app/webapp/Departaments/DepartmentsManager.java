package pl.jazapp.app.webapp.Departaments;

import pl.jazapp.app.Departments.DepartmentCreatorService;
import pl.jazapp.app.Departments.DepartmentEditService;
import pl.jazapp.app.Departments.DepartmentEntity;
import pl.jazapp.app.Departments.DepartmentSearchService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class DepartmentsManager {
    @Inject
    private DepartmentCreatorService departmentCreator;
    @Inject
    private DepartmentSearchService departmentSearch;
    @Inject
    private DepartmentEditService departmentEdit;

    public String addNewDepartment(DepartmentRequest req)
    {
        if(departmentSearch.findDepartment(req.getId()).isEmpty() || req.getId() == 0)
        {
            departmentCreator.createDepartament(req.getName());
        }
        else
        {
            departmentEdit.editDepartament(req.getName(), req.getId());
        }

        return "/departments/list.xhtml?faces-redirect=true";
    }

    public String findDepartmentByIdAndReturnItsName(Long id)
    {
        var departmentEntity = departmentSearch.findDepartment(id);
        if(departmentEntity.isEmpty())
            return "";
        else
            return departmentEntity.get().getName();
    }

    public List<DepartmentEntity> getAllDepartments()
    {
        return departmentSearch.getAllDepartments();
    }
}
