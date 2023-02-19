package jupgo.jupgoserver.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jupgo.jupgoserver.service.AuthService;
import jupgo.jupgoserver.service.JwtService;
import jupgo.jupgoserver.service.UserService;
import jupgo.jupgoserver.util.response.Response;
import jupgo.jupgoserver.util.response.StatusMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static jupgo.jupgoserver.util.response.StatusCode.*;
import static jupgo.jupgoserver.util.response.StatusCode.INTERNAL_ERROR;

@Controller
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;


    @ResponseBody
    @GetMapping("/login")
    public Response login(@RequestParam String code) {
        try {
            return new Response(OK.getCode(), StatusMessage.LOGIN_SUCCESS.getMessage(), authService.kakaoLogin(code));
        } catch (Exception e) {
            return new Response(INTERNAL_ERROR.getCode(), StatusMessage.INTERNAL_ERROR.getMessage());
        }
    }

    @ResponseBody
    @DeleteMapping("")
    public Response withdraw(@RequestHeader("Authorization") String authorizationHeader) throws Exception {
        try {
            long userId = jwtService.decode(authorizationHeader.split(" ")[1]).getUser_id();
            userService.deleteUser(userId);
            return new Response(OK.getCode(), StatusMessage.WITHDRAW_SUCCESS.getMessage());
        } catch (JWTVerificationException e) {
            System.out.println(e);
            return new Response(UNAUTHORIZED.getCode(), StatusMessage.INVALID_TOKEN.getMessage());
        } catch (Exception e) {
            return new Response(INTERNAL_ERROR.getCode(), StatusMessage.INTERNAL_ERROR.getMessage());
        }
    }
}