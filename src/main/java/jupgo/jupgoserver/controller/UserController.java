package jupgo.jupgoserver.controller;

import com.google.gson.JsonObject;
import jupgo.jupgoserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("/login")
    public void login(@RequestParam String code) throws Exception {
        String access_Token = userService.getKaKaoAccessToken(code);
        userService.createKakaoUser(access_Token);
    }
}
