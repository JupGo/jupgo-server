package jupgo.jupgoserver.repository;
import jupgo.jupgoserver.domain.user.User;
import javax.persistence.EntityManager;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final EntityManager em;

    public UserRepository(EntityManager em) {
        this.em = em;
    }

    public User save(User user) {
        em.persist(user);
        return user;
    }

    public Optional<User> findByEmail(String email) {
        User user = em.find(User.class, email);
        return Optional.ofNullable(user);
    }
}
