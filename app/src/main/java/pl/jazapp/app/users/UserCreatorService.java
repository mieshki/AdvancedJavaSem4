package pl.jazapp.app.users;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.jazapp.app.webapp.register.RegisterRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class UserCreatorService {
    @PersistenceContext
    private EntityManager em;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public void createUser(RegisterRequest req) {
        var userEntity = new UserEntity();
        userEntity.setUsername(req.getUsername());
        userEntity.setPassword(passwordEncoder.encode(req.getPassword()));
        userEntity.setBirthday(req.getBirthdate());
        userEntity.setEmail(req.getEmail());
        userEntity.setFirst_name(req.getName());
        userEntity.setLast_name(req.getSurname());

        var isPasswordCorrect = passwordEncoder.matches(req.getPassword(), userEntity.getPassword());
        /*if(!isPasswordCorrect){
            throw new Exception();
        }*/

        em.persist(userEntity);
    }
}
