package jupgo.jupgoserver.controller;

import static jupgo.jupgoserver.util.response.StatusCode.INTERNAL_ERROR;
import static jupgo.jupgoserver.util.response.StatusCode.OK;
import static jupgo.jupgoserver.util.response.StatusCode.UNAUTHORIZED;
import static jupgo.jupgoserver.util.response.StatusMessage.*;

import com.auth0.jwt.exceptions.JWTVerificationException;
import java.util.List;
import jupgo.jupgoserver.domain.tree.Tree;
import jupgo.jupgoserver.dto.diary.SaveDiaryRequestDto;
import jupgo.jupgoserver.dto.diary.SaveDiaryResponseDto;
import jupgo.jupgoserver.dto.tree.ReturnTreeContainDiariesDto;
import jupgo.jupgoserver.service.DiaryService;
import jupgo.jupgoserver.service.JwtService;
import jupgo.jupgoserver.service.S3Service;
import jupgo.jupgoserver.service.TreeService;
import jupgo.jupgoserver.service.UserService;
import jupgo.jupgoserver.util.response.Response;
import jupgo.jupgoserver.util.response.StatusMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/diary")
public class DiaryController {
    @Autowired
    private DiaryService diaryService;
    @Autowired
    private S3Service s3Service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private TreeService treeService;

    @ResponseBody
    @PostMapping()
    public Response saveDiary(
            @RequestPart("diaryData") SaveDiaryRequestDto saveDiaryRequestDto,
            @RequestParam("file") MultipartFile file,
            @RequestHeader("Authorization") String authorizationHeader)
            throws Exception {
        try {
            long userId = jwtService.decode(authorizationHeader.split(" ")[1]).getUser_id();
            long treeId = userService.getCurrentTreeIdByUserId(userId);
            if (treeId == -1) {
                Tree treeCreated  = treeService.createTreeInUser(userId);
                treeId = treeCreated.getId();
            }
            Tree tree = treeService.getTreeById(treeId);
            String fileLink = s3Service.getLinkAfterSaveUploadFile(file);
            saveDiaryRequestDto.setPhoto(fileLink);
            saveDiaryRequestDto.setTree(tree);
            SaveDiaryResponseDto saveDiaryResponseDto = diaryService.saveDiary(saveDiaryRequestDto);
            treeService.addExperience(tree, saveDiaryRequestDto.getDistance(), saveDiaryRequestDto.getDuration());
            return new Response(OK.getCode(), PLOGGING_SAVE_SUCCESS.getMessage(), saveDiaryResponseDto);
        } catch (JWTVerificationException e) {
            System.out.println(e);
            return new Response(UNAUTHORIZED.getCode(), StatusMessage.INVALID_TOKEN.getMessage());
        }
        catch (Exception e) {
            System.out.println(e);
            return new Response(INTERNAL_ERROR.getCode(), StatusMessage.INTERNAL_ERROR.getMessage());
        }
    }

    @ResponseBody
    @GetMapping("/{treeId}")
    public Response getDiariesOfTree(
            @RequestHeader("Authorization") String authorizationHeader,
            @PathVariable("treeId") Long treeId){
        try {
            long userId = jwtService.decode(authorizationHeader.split(" ")[1]).getUser_id();
            ReturnTreeContainDiariesDto returnTreeContainDiariesDto = treeService.getDiariesByTreeIdAfterValidateAuthorization(treeId, userId);
            return new Response(OK.getCode(), GET_DIARIES_OF_TREE_SUCCESS.getMessage(), returnTreeContainDiariesDto);
        } catch (JWTVerificationException e) {
            System.out.println(e);
            return new Response(UNAUTHORIZED.getCode(), StatusMessage.INVALID_TOKEN.getMessage());
        } catch (Exception e) {
            System.out.println(e);
            return new Response(INTERNAL_ERROR.getCode(), StatusMessage.INTERNAL_ERROR.getMessage());
        }
    }

    @ResponseBody
    @GetMapping("/all")
    public Response getDiariesOfAllTrees(@RequestHeader("Authorization") String authorizationHeader){
        long userId = jwtService.decode(authorizationHeader.split(" ")[1]).getUser_id();
        List<Tree> trees = userService.getTreesByUserId(userId);
        List<ReturnTreeContainDiariesDto> returnTreeContainDiariesDtos = treeService.getAllDiariesByTrees(trees);
        return new Response(OK.getCode(), GET_ALL_DIARIES.getMessage(), returnTreeContainDiariesDtos);
    }

    @ResponseBody
    @GetMapping("/test2")
    public Response diaryTest2(@RequestHeader("Authorization") String authorizationHeader) {
        System.out.println(authorizationHeader);
        String[] tokenArray = authorizationHeader.split(" ");
        String token = tokenArray[1];
        System.out.println(token);
        System.out.println(jwtService.decode(token));
        return new Response(OK.getCode(), PLOGGING_SAVE_SUCCESS.getMessage(), "hi");
    }
}
