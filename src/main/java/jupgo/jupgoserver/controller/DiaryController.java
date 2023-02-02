package jupgo.jupgoserver.controller;

import jupgo.jupgoserver.domain.diary.Diary;
import jupgo.jupgoserver.dto.DiaryRequestDto;
import jupgo.jupgoserver.service.DiaryService;
import jupgo.jupgoserver.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
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

    @ResponseBody
    @PostMapping()
    public Diary saveDiary(
            @RequestPart("diaryData") DiaryRequestDto diaryRequestDto,
            @RequestParam("file") MultipartFile file)
            throws Exception {
        String fileLink = s3Service.getLinkAfterSaveUploadFile(file);
        diaryRequestDto.setPhoto(fileLink);
        return diaryService.saveDiary(diaryRequestDto);
    }
}
