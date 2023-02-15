package jupgo.jupgoserver.service;


import jupgo.jupgoserver.domain.user.User;
import jupgo.jupgoserver.dto.user.SaveUserRequestDto;
import jupgo.jupgoserver.dto.user.SaveUserResponseDto;
import jupgo.jupgoserver.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {


    private UserRepository userRepository;
    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    public boolean existsByKakaoUserId(Integer kakaoUserId) {
        User user = userRepository.findByKakaoUserId(kakaoUserId);
        if (user == null)
            return false;
        else
            return true;
    }

    public SaveUserResponseDto save(SaveUserRequestDto saveUserRequestDto) {
        if(saveUserRequestDto.getEmail()==null){
            saveUserRequestDto.setEmail("");
        }
        User user = new User(saveUserRequestDto);
        userRepository.save(user);
        return new SaveUserResponseDto(user);
    }
}
