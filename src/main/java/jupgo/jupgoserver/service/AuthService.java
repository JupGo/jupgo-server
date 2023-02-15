package jupgo.jupgoserver.service;

import com.fasterxml.jackson.databind.JsonNode;
import jupgo.jupgoserver.domain.user.User;
import jupgo.jupgoserver.repository.UserRepository;
import jupgo.jupgoserver.util.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static jupgo.jupgoserver.util.response.StatusCode.*;
import static jupgo.jupgoserver.util.response.StatusMessage.LOGIN_FAIL;
import static jupgo.jupgoserver.util.response.StatusMessage.LOGIN_SUCCESS;

@Service
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final KakaoService kakaoService;

    public AuthService(UserRepository userRepository, JwtService jwtService, KakaoService kakaoService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.kakaoService = kakaoService;
    }

    public Response<JwtService.TokenRes> kakaoLogin(final String accessToken) {
        int userKakaoId = -1;
            JsonNode userInfo = kakaoService.verifyAccessToken(accessToken);
            if(!userInfo.path("id").isMissingNode()){
                userKakaoId = userInfo.path("id").asInt();
            }else {
                return new Response(BAD_REQUEST.getCode(), LOGIN_FAIL.getMessage());
            }
        final User user = userRepository.findByKakaoUserId(userKakaoId);
        if(user!=null){
            Long userId = user.getId();
            final JwtService.TokenRes tokenDto = new JwtService.TokenRes(jwtService.create(userId));
            return new Response(OK.getCode(), LOGIN_SUCCESS.getMessage(), tokenDto);
        }
        return new Response(NOT_FOUND.getCode(), LOGIN_FAIL.getMessage());
    }


}