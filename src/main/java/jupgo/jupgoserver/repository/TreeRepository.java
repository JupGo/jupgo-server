package jupgo.jupgoserver.repository;

import jupgo.jupgoserver.domain.tree.Tree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class TreeRepository {
    private final EntityManager em;

    public TreeRepository(EntityManager em) {
        this.em = em;
    }

    public List<Tree> findByUserId(long userId) {
        String query = "SELECT t FROM Tree as t WHERE t.user.id = :userId";
        List<Tree> treeList = em.createQuery(query, Tree.class)
                .setParameter("userId", userId)
                .getResultList();
        return treeList;
    }

    public Tree findByTreeId(long treeId) {
        String query = "SELECT t FROM Tree as t WHERE t.id = :treeId";
        Tree tree = em.createQuery(query, Tree.class)
                .setParameter("treeId", treeId)
                .getSingleResult();
        return tree;
    }
}
