package jupgo.jupgoserver.service;

import com.fasterxml.jackson.databind.JsonNode;
import jupgo.jupgoserver.domain.user.User;
import jupgo.jupgoserver.dto.user.SaveUserResponseDto;
import jupgo.jupgoserver.repository.UserRepository;
import jupgo.jupgoserver.util.response.Response;
import jupgo.jupgoserver.util.response.StatusMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static jupgo.jupgoserver.util.response.StatusCode.INTERNAL_ERROR;

@Service
@Slf4j
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final KakaoService kakaoService;
    private final UserService userService;

    public AuthService(UserRepository userRepository, JwtService jwtService, KakaoService kakaoService, UserService userService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.kakaoService = kakaoService;
        this.userService = userService;
    }

    public Object kakaoLogin(final String code) throws Exception {
        long userKakaoId = -1;
        String accessToken = kakaoService.verifyAccessToken(code);
        JsonNode userInfo = kakaoService.getUserInfo(accessToken);

        try {
            userKakaoId = userInfo.path("id").asLong();
        }
        catch (Exception e) {
            return new Response(INTERNAL_ERROR.getCode(), StatusMessage.INTERNAL_ERROR.getMessage());
        }

        List<User> user = userRepository.findByKakaoUserId(userKakaoId);
        System.out.println("userInfo:"+  userInfo);
        if (!user.isEmpty()) {
            Long userId = user.get(0).getId();
            final JwtService.TokenRes tokenDto = new JwtService.TokenRes(jwtService.create(userId));
            return tokenDto;
        } else {
            User newUser = new User();
            String email = userInfo.path("kakao_account").path("email").asText();
            newUser.setKakaoId(userInfo.path("id").longValue());

            JsonNode kakao_account = userInfo.path("kakao_account");
            if (kakao_account.path("has_email").asBoolean() && kakao_account.path("is_email_valid").asBoolean() && kakao_account.path("is_email_verified").asBoolean()) {
                newUser.setEmail(kakao_account.path("email").asText());
            }

            JsonNode properties = userInfo.path("properties");
            if (properties.path("nickname").asText() != null) {
                newUser.setNickname(properties.path("nickname").asText());
                System.out.println("nickname: " + properties.path("nickname").asText());
            } else {
                newUser.setNickname("no_name");
                System.out.println("no_name");
            }

            try {
                SaveUserResponseDto saveUserResponseDto = userService.save(newUser);
                final JwtService.TokenRes tokenDto = new JwtService.TokenRes(jwtService.create(saveUserResponseDto.getId()));
                return tokenDto;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}