package jupgo.jupgoserver.controller;

import com.fasterxml.jackson.databind.JsonNode;
import jupgo.jupgoserver.domain.user.User;
import jupgo.jupgoserver.dto.user.SaveUserRequestDto;
import jupgo.jupgoserver.dto.user.SaveUserResponseDto;
import jupgo.jupgoserver.service.AuthService;
import jupgo.jupgoserver.service.KakaoService;
import jupgo.jupgoserver.service.UserService;
import jupgo.jupgoserver.util.response.Response;
import jupgo.jupgoserver.util.response.StatusMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static jupgo.jupgoserver.util.response.StatusCode.*;
import static jupgo.jupgoserver.util.response.StatusCode.INTERNAL_ERROR;
import static jupgo.jupgoserver.util.response.StatusMessage.SIGNUP_SUCCESS;

@Controller
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private KakaoService kakaoService;
    @Autowired
    private AuthService authService;


    @ResponseBody
    @PostMapping("/login")
    public Response login(@RequestBody String accessToken) {
        try {
            return new Response(OK.getCode(), StatusMessage.LOGIN_SUCCESS.getMessage(), authService.kakaoLogin(accessToken));
        }
        catch (Exception e) {
            e.printStackTrace();
            return new Response(INTERNAL_ERROR.getCode(), StatusMessage.INTERNAL_ERROR.getMessage());
        }
    }

    @ResponseBody
    @PostMapping("/signup")
    public Response signup(@RequestBody SaveUserRequestDto saveUserRequestDto) {
        JsonNode userInfo = kakaoService.verifyAccessToken(saveUserRequestDto.getAccessToken());
        String email = userInfo.path("kakao_account").path("email").asText();
        System.out.println("email : " + email);
        saveUserRequestDto.setKakaoId(userInfo.path("id").asInt());

        JsonNode kakao_account = userInfo.path("kakao_account");
        if (kakao_account.path("has_email").asBoolean() && kakao_account.path("is_email_valid").asBoolean() && kakao_account.path("is_email_verified").asBoolean()) {
            saveUserRequestDto.setEmail(kakao_account.path("email").asText());
        }

        JsonNode properties = userInfo.path("properties");
        if(properties.path("nickname").asText()!=null) {
            saveUserRequestDto.setNickname(properties.path("nickname").asText());
            System.out.println("nickname: "+userInfo.path("nickname").asText());
        } else{
            saveUserRequestDto.setNickname("no_name");
            System.out.println("no_name");
        }

        try {
            SaveUserResponseDto saveUserResponseDto = userService.save(saveUserRequestDto);
            return new Response(OK.getCode(), SIGNUP_SUCCESS.getMessage(), saveUserResponseDto);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(INTERNAL_ERROR.getCode(), StatusMessage.INTERNAL_ERROR.getMessage());
        }
    }
}
