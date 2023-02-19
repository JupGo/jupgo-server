package jupgo.jupgoserver.service;
import jupgo.jupgoserver.domain.tree.Tree;
import jupgo.jupgoserver.dto.tree.GetTreeAllResponseDto;
import jupgo.jupgoserver.dto.tree.GetTreeResponseDto;
import jupgo.jupgoserver.repository.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TreeService {

    @Autowired
    private TreeRepository treeRepository;

    public TreeService(TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    public GetTreeAllResponseDto getAll(long userId) {
        List<Tree> treeList = treeRepository.findByUserId(userId);
        return new GetTreeAllResponseDto(treeList);
    }

    public GetTreeResponseDto get(long id) {
        Tree tree = treeRepository.findByTreeId(id);
        return new GetTreeResponseDto(tree);
    }
}
