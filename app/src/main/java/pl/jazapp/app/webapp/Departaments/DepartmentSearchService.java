package pl.jazapp.app.webapp.Departaments;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DepartmentSearchService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Optional<DepartmentEntity> findDepartment(String name)
    {
        return em.createQuery("from DepartmentEntity where name = :name", DepartmentEntity.class)
                .setParameter("name", name)
                .getResultList().stream()
                .findFirst();
    }

    @Transactional
    public Optional<DepartmentEntity> findDepartment(Long id)
    {
        return em.createQuery("from DepartmentEntity where id = :id", DepartmentEntity.class)
                .setParameter("id", id)
                .getResultList().stream()
                .findFirst();
    }

    public List<DepartmentEntity> getAllDepartments()
    {
        return em.createNamedQuery("Department.findAll", DepartmentEntity.class).getResultList();
    }

}

