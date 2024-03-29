package jupgo.jupgoserver.service;


import java.util.Comparator;
import java.util.List;
import jupgo.jupgoserver.domain.tree.Tree;
import jupgo.jupgoserver.domain.user.User;
import jupgo.jupgoserver.dto.user.SaveUserResponseDto;
import jupgo.jupgoserver.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService {
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    public SaveUserResponseDto save(User user) {
        if(user.getEmail()==null){
            user.setEmail("");
        }
        userRepository.save(user);
        return new SaveUserResponseDto(user);
    }

    public long getCurrentTreeIdByUserId(long userId) {
        System.out.println("check in getCurrentTreeIdByUserId");
        System.out.println("userId: " + userId);
        User user = userRepository.findById(userId);
        System.out.println("user: " + user);
        System.out.println(user.getId() + " / " + user.getNickname());
        List<Tree> trees = user.getTrees();
        System.out.println("trees of user: " + trees);
        if (trees.isEmpty()) {
            return -1;
        }
        Tree currentTree = trees.stream()
                .max(Comparator.comparing(Tree::getId))
                .get();
        return currentTree.getId();
    }

    public List<Tree> getTreesByUserId(long userId) {
        return userRepository.findById(userId).getTrees();
    }

    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

    public User getUserById(long userId) {
        return userRepository.findById(userId);
    }
}
