package jupgo.jupgoserver.repository;

import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class DiaryRepository {
    private final EntityManager em;

    public DiaryRepository(EntityManager em) {
        this.em = em;
    }

}
