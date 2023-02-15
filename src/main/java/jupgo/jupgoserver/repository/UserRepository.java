package jupgo.jupgoserver.repository;
import jupgo.jupgoserver.domain.user.User;
import javax.persistence.EntityManager;
import java.util.Optional;

import jupgo.jupgoserver.dto.user.SaveUserRequestDto;
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

    public User findByKakaoUserId(Integer kakaoUserId) {
        User user = em.find(User.class, kakaoUserId);
        return user;
    }
}
