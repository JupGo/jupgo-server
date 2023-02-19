package jupgo.jupgoserver.controller;

import jupgo.jupgoserver.dto.tree.GetTreeAllResponseDto;
import jupgo.jupgoserver.dto.tree.GetTreeResponseDto;
import jupgo.jupgoserver.service.JwtService;
import jupgo.jupgoserver.service.TreeService;
import jupgo.jupgoserver.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static jupgo.jupgoserver.util.response.StatusCode.OK;
import static jupgo.jupgoserver.util.response.StatusMessage.PLOGGING_SAVE_SUCCESS;

@Controller
@RequestMapping("/api/tree")
public class TreeController {

    @Autowired
    private TreeService treeService;

    @Autowired
    private JwtService jwtService;

    @ResponseBody
    @GetMapping("/all")
    public Response getAll(@RequestHeader Map<String, String> headers) {
        String accessToken = headers.get("access_token");
        long userId = jwtService.decode(accessToken);
        GetTreeAllResponseDto getTreeAllResponseDto = treeService.getAll(userId);
        return new Response(OK.getCode(), PLOGGING_SAVE_SUCCESS.getMessage(), getTreeAllResponseDto);
    }

    @ResponseBody
    @GetMapping("/{treeId}")
    public Response get(@PathVariable("treeId") Long treeId) {
        GetTreeResponseDto getTreeResponseDto = treeService.get(treeId);
        return new Response(OK.getCode(), PLOGGING_SAVE_SUCCESS.getMessage(), getTreeResponseDto);
    }
}
