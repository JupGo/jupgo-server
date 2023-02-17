package jupgo.jupgoserver.controller;

import static jupgo.jupgoserver.util.response.StatusCode.OK;
import static jupgo.jupgoserver.util.response.StatusMessage.*;

import jupgo.jupgoserver.dto.diary.SaveDiaryRequestDto;
import jupgo.jupgoserver.dto.diary.SaveDiaryResponseDto;
import jupgo.jupgoserver.service.DiaryService;
import jupgo.jupgoserver.service.S3Service;
import jupgo.jupgoserver.util.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/diary")
public class DiaryController {
    @Autowired
    private DiaryService diaryService;
    @Autowired
    private S3Service s3Service;

    @ResponseBody
    @PostMapping()
    public Response saveDiary(
            @RequestPart("diaryData") SaveDiaryRequestDto saveDiaryRequestDto,
            @RequestParam("file") MultipartFile file)
            throws Exception {
        String fileLink = s3Service.getLinkAfterSaveUploadFile(file);
        saveDiaryRequestDto.setPhoto(fileLink);
        SaveDiaryResponseDto saveDiaryResponseDto = diaryService.saveDiary(saveDiaryRequestDto);
        return new Response(OK.getCode(), PLOGGING_SAVE_SUCCESS.getMessage(), saveDiaryResponseDto);
    }
}
