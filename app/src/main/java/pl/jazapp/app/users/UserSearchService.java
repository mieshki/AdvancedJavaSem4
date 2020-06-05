package pl.jazapp.app.users;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class UserSearchService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Optional<UserEntity> findUser(String username) {
        return em.createQuery("from UserEntity where username = :username", UserEntity.class)
                .setParameter("username", username)
                .getResultList().stream()
                .findFirst();
    }

}
