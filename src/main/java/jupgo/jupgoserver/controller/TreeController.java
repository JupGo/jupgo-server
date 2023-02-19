package jupgo.jupgoserver.controller;


import static jupgo.jupgoserver.util.response.StatusCode.INTERNAL_ERROR;
import static jupgo.jupgoserver.util.response.StatusCode.OK;
import static jupgo.jupgoserver.util.response.StatusCode.UNAUTHORIZED;
import static jupgo.jupgoserver.util.response.StatusMessage.GET_CURRENT_TREE_SUCCESS;
import static jupgo.jupgoserver.util.response.StatusMessage.GET_MY_TREES_SUCCESS;
import static jupgo.jupgoserver.util.response.StatusMessage.INVALID_TOKEN;
import static jupgo.jupgoserver.util.response.StatusMessage.PLOGGING_SAVE_SUCCESS;

import com.auth0.jwt.exceptions.JWTVerificationException;
import java.util.List;
import jupgo.jupgoserver.domain.tree.Tree;
import jupgo.jupgoserver.domain.user.User;
import jupgo.jupgoserver.dto.tree.ReturnTreeContainDiariesDto;
import jupgo.jupgoserver.dto.tree.ReturnTreeDto;
import jupgo.jupgoserver.service.JwtService;
import jupgo.jupgoserver.service.TreeService;
import jupgo.jupgoserver.service.UserService;
import jupgo.jupgoserver.util.response.Response;
import jupgo.jupgoserver.util.response.StatusCode;
import jupgo.jupgoserver.util.response.StatusMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/tree")
public class TreeController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private TreeService treeService;

    @ResponseBody
    @GetMapping("/all")
    public Response getAllTrees(@RequestHeader("Authorization") String authorizationHeader) throws Exception {
        try {
            long userId = jwtService.decode(authorizationHeader.split(" ")[1]).getUser_id();
            User user = userService.getUserById(userId);
            List<ReturnTreeDto> returnTreeDtos = treeService.getAllTreesByUser(user);
            return new Response(OK.getCode(), GET_MY_TREES_SUCCESS.getMessage(), returnTreeDtos);
        } catch (JWTVerificationException e) {
            System.out.println(e);
            return new Response(UNAUTHORIZED.getCode(), INVALID_TOKEN.getMessage());
        }
        catch (Exception e) {
            System.out.println(e);
            return new Response(INTERNAL_ERROR.getCode(), StatusMessage.INTERNAL_ERROR.getMessage());
        }
    }

    @ResponseBody
    @GetMapping("/target")
    public Response getCurrentTree(@RequestHeader("Authorization") String authorizationHeader) throws Exception {
        try {
            long userId = jwtService.decode(authorizationHeader.split(" ")[1]).getUser_id();
            long treeId = userService.getCurrentTreeIdByUserId(userId);
            if (treeId == -1) {
                Tree treeCreated  = treeService.createTreeInUser(userId);
                treeId = treeCreated.getId();
            }
            Tree tree = treeService.getTreeById(treeId);
            ReturnTreeDto returnTreeDto = new ReturnTreeDto(tree);
            return new Response(OK.getCode(), GET_CURRENT_TREE_SUCCESS.getMessage(), returnTreeDto);
        } catch (JWTVerificationException e) {
            System.out.println(e);
            return new Response(UNAUTHORIZED.getCode(), INVALID_TOKEN.getMessage());
        }
        catch (Exception e) {
            System.out.println(e);
            return new Response(INTERNAL_ERROR.getCode(), StatusMessage.INTERNAL_ERROR.getMessage());
        }
    }
}
