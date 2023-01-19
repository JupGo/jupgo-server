package jupgo.jupgoserver.repository;
import jupgo.jupgoserver.dto.UserDto;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private final EntityManager em;

    public UserRepository(EntityManager em) {
        this.em = em;
    }

    public UserDto save(UserDto userDto) {
        em.persist(userDto);
        return userDto;
    }


    public Optional<UserDto> findByEmail(String email) {
        UserDto userDto = em.find(UserDto.class, email);
        return Optional.ofNullable(userDto);
    }
}
