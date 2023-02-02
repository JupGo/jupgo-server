package jupgo.jupgoserver.service;

import java.io.IOException;
import jupgo.jupgoserver.domain.diary.Diary;
import jupgo.jupgoserver.dto.DiaryRequestDto;
import jupgo.jupgoserver.repository.DiaryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DiaryService {

    private DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public void saveDiary(DiaryRequestDto diaryRequestDto) {
        Diary diary = new Diary(diaryRequestDto);
        diaryRepository.save(diary);
        System.out.println("Duration after save : " + diary.getDuration());
    }
}
