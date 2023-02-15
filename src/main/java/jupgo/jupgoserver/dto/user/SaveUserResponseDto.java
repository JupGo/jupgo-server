package jupgo.jupgoserver.dto.user;

import jupgo.jupgoserver.domain.diary.Diary;
import jupgo.jupgoserver.domain.user.User;

public class SaveUserResponseDto {

    private Long id;

    public SaveUserResponseDto(User user) {
        this.id = user.getId();
    }

    public Long getId() {
        return id;
    }
}
