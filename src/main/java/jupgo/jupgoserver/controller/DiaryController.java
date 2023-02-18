package jupgo.jupgoserver.controller;

import static jupgo.jupgoserver.util.response.StatusCode.OK;
import static jupgo.jupgoserver.util.response.StatusMessage.*;

import jupgo.jupgoserver.domain.tree.Tree;
import jupgo.jupgoserver.dto.diary.SaveDiaryRequestDto;
import jupgo.jupgoserver.dto.diary.SaveDiaryResponseDto;
import jupgo.jupgoserver.service.DiaryService;
import jupgo.jupgoserver.service.JwtService;
import jupgo.jupgoserver.service.S3Service;
import jupgo.jupgoserver.service.TreeService;
import jupgo.jupgoserver.service.UserService;
import jupgo.jupgoserver.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
        long userId = jwtService.decode(authorizationHeader.split(" ")[1]).getUser_id();
        long treeId = userService.getCurrentTreeIdByUserId(userId);
        if (treeId == -1) {
            treeService.saveTreeInUser(userId);
            treeId = userService.getCurrentTreeIdByUserId(userId);
        }
        Tree tree = treeService.getTreeById(treeId);
        String fileLink = s3Service.getLinkAfterSaveUploadFile(file);
        saveDiaryRequestDto.setPhoto(fileLink);
        saveDiaryRequestDto.setTree(tree);
        SaveDiaryResponseDto saveDiaryResponseDto = diaryService.saveDiary(saveDiaryRequestDto);
        return new Response(OK.getCode(), PLOGGING_SAVE_SUCCESS.getMessage(), saveDiaryResponseDto);
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
