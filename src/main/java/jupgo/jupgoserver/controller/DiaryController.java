package jupgo.jupgoserver.controller;

import jupgo.jupgoserver.dto.DiaryRequestDto;
import jupgo.jupgoserver.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/diary")
public class DiaryController {
    @Autowired
    private DiaryService diaryService;

    @ResponseBody
    @PostMapping()
    public void saveDiary(@RequestBody DiaryRequestDto diaryRequestDto) throws Exception {
        System.out.println(diaryRequestDto);
        diaryService.saveDiary(diaryRequestDto);
    }
}
