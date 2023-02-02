package jupgo.jupgoserver.service;

import jupgo.jupgoserver.domain.diary.Diary;
import jupgo.jupgoserver.dto.diary.SaveDiaryRequestDto;
import jupgo.jupgoserver.dto.diary.SaveDiaryResponseDto;
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

    public SaveDiaryResponseDto saveDiary(SaveDiaryRequestDto saveDiaryRequestDto) {
        Diary diary = new Diary(saveDiaryRequestDto);
        diaryRepository.save(diary);
        return new SaveDiaryResponseDto(diary);
    }
}
