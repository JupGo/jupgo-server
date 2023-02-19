package jupgo.jupgoserver.service;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import jupgo.jupgoserver.domain.tree.Tree;
import jupgo.jupgoserver.domain.user.User;
import jupgo.jupgoserver.dto.tree.ReturnTreeContainDiariesDto;
import jupgo.jupgoserver.dto.tree.ReturnTreeDto;
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

    public Tree createTreeInUser(long userId) {
        User user = userRepository.findById(userId);
        Tree tree = createTree(user);
        treeRepository.save(tree);
        user = userRepository.findById(userId);
        return tree;
    }

    private Tree createTree(User user) {
        return new Tree(user);
    }

    public Tree getTreeById(long treeId) {
        return treeRepository.findById(treeId);
    }

    public void addExperience(Tree tree, int distance, Duration duration) {
        tree.addExperience(distance, duration);
        treeRepository.save(tree);
    }

    public ReturnTreeContainDiariesDto getDiariesByTreeIdAfterValidateAuthorization(Long treeId, Long userId) {
        Tree tree = treeRepository.findById(treeId);
        if (tree.getUser().getId() != userId) {
            throw new IllegalArgumentException("로그인 한 유저의 Tree가 아닙니다.");
        }
        return new ReturnTreeContainDiariesDto(tree);
    }

    private ReturnTreeContainDiariesDto getDiariesByTreeId(Long treeId) {
        Tree tree = treeRepository.findById(treeId);
        return new ReturnTreeContainDiariesDto(tree);
    }

    public List<ReturnTreeContainDiariesDto> getAllDiariesByTrees(List<Tree> trees) {
        return trees.stream()
                .map(tree -> getDiariesByTreeId(tree.getId()))
                .collect(Collectors.toList());
    }

    public List<ReturnTreeDto> getAllTreesByUser(User user) {
        return user.getTrees().stream()
                .map(ReturnTreeDto::new)
                .collect(Collectors.toList());
    }
}
