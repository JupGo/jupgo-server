package jupgo.jupgoserver.repository;

import javax.persistence.EntityManager;
import jupgo.jupgoserver.domain.tree.Tree;
import org.springframework.stereotype.Repository;

@Repository
public class TreeRepository {
    private final EntityManager em;

    public TreeRepository(EntityManager em) {
        this.em = em;
    }

    public Tree save(Tree tree) {
        em.persist(tree);
        return tree;
    }
}
