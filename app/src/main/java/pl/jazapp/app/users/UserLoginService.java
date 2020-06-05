package pl.jazapp.app.users;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.jazapp.app.webapp.login.LoginRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class UserLoginService {
    @PersistenceContext
    private EntityManager em;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public Optional<UserEntity> findUser(String username) {
        return em.createQuery("from UserEntity where username = :username", UserEntity.class)
                .setParameter("username", username)
                .getResultList().stream()
                .findFirst();
    }

    public String getWelcomeString(LoginRequest req)
    {
        var user = findUser(req.getUsername());

        if(user.isPresent()){
            String result = "Hi " + user.get().getFirst_name() + " " + user.get().getLast_name() + "!";
            return result;
        }

        return "";
    }

    @Transactional
    public boolean canLoginUser(LoginRequest req) {
        var user = findUser(req.getUsername());

        if(user.isPresent()){
            String encodedPassword = passwordEncoder.encode(req.getPassword());
            String userPassword = user.get().getPassword();

            if(passwordEncoder.matches(req.getPassword(), userPassword)){
                return true;
            }
        }

        return false;
    }
}
