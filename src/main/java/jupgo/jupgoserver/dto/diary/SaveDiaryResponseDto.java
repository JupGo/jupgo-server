package jupgo.jupgoserver.dto.diary;

import jupgo.jupgoserver.domain.diary.Diary;

public class SaveDiaryResponseDto {
    private Long id;

    public SaveDiaryResponseDto(Diary diary) {
        this.id = diary.getId();
    }

    public Long getId() {
        return id;
    }
}
