package jupgo.jupgoserver.repository;

import javax.persistence.EntityManager;
import jupgo.jupgoserver.domain.diary.Diary;
import jupgo.jupgoserver.domain.user.User;
import org.springframework.stereotype.Repository;

@Repository
public class DiaryRepository {
    private final EntityManager em;

    public DiaryRepository(EntityManager em) {
        this.em = em;
    }

    public Diary save(Diary diary) {
        em.persist(diary);
        return diary;
    }
}
