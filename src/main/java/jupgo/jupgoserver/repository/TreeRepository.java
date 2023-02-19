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

    public void save(Tree tree) {
        em.persist(tree);
    }

    public Tree findById(long treeId) {
        return em.createQuery("select tree from Tree tree where tree.id = :treeId", Tree.class)
                .setParameter("treeId", treeId)
                .getSingleResult();
    }
}
