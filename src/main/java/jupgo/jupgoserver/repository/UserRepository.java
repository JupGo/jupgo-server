package jupgo.jupgoserver.repository;
import jupgo.jupgoserver.domain.user.User;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
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

    public List<User> findByKakaoUserId(String kakaoUserId) {
        List<User> result = em.createQuery("select u from User u where u.kakaoId = :kakaoUserId", User.class)
                .setParameter("kakaoUserId", kakaoUserId)
                .getResultList();
        return result;
    }

    public User findById(long userId) {
        List<User> users = em.createQuery("select u from User u where u.id = :userId", User.class)
                .setParameter("userId", userId)
                .getResultList();
        return users.get(0);
    }

}
