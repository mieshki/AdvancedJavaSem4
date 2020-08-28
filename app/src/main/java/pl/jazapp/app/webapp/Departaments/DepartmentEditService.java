package pl.jazapp.app.webapp.Departaments;

import pl.jazapp.app.users.UserEntity;
import pl.jazapp.app.users.UserLoginService;
import pl.jazapp.app.webapp.register.RegisterRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class DepartmentEditService {
    @PersistenceContext
    private EntityManager em;
    @Inject
    private DepartmentSearchService departmentSearch;

    @Transactional
    public void editDepartament(String newDepartmentName, Long departmentId) {
        var departmentEntity = new DepartmentEntity();
        departmentEntity.setId(departmentId);
        departmentEntity.setName(newDepartmentName);

        em.merge(departmentEntity);
    }
}
