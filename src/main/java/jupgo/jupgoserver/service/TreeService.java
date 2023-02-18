package jupgo.jupgoserver.service;

import jupgo.jupgoserver.domain.tree.Tree;
import jupgo.jupgoserver.domain.user.User;
import jupgo.jupgoserver.repository.TreeRepository;
import jupgo.jupgoserver.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TreeService {

    private final TreeRepository treeRepository;
    private final UserRepository userRepository;

    public TreeService(TreeRepository treeRepository, UserRepository userRepository) {
        this.treeRepository = treeRepository;
        this.userRepository = userRepository;
    }

    public void saveTreeInUser(long userId) {
        User user = userRepository.findById(userId);
        Tree tree = createTree(user);
        treeRepository.save(tree);
    }

    private Tree createTree(User user) {
        return new Tree(user);
    }
}