package jupgo.jupgoserver.service;


import jupgo.jupgoserver.domain.user.User;
import jupgo.jupgoserver.dto.user.SaveUserResponseDto;
import jupgo.jupgoserver.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
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
}
