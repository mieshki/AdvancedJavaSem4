package pl.jazapp.app.Departments;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class DepartmentCreatorService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void createDepartament(String departmentName) {
        var departmentEntity = new DepartmentEntity();
        departmentEntity.setName(departmentName);

        em.persist(departmentEntity);
    }
}
