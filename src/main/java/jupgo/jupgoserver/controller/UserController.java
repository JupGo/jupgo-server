package jupgo.jupgoserver.controller;

import jupgo.jupgoserver.service.AuthService;
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


    @ResponseBody
    @GetMapping("/login")
    public Response login(@RequestParam String code) {
        try {
            return new Response(OK.getCode(), StatusMessage.LOGIN_SUCCESS.getMessage(), authService.kakaoLogin(code));
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(INTERNAL_ERROR.getCode(), StatusMessage.INTERNAL_ERROR.getMessage());
        }
    }
}