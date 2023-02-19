package jupgo.jupgoserver.dto.diary;

import java.time.Duration;
import java.time.LocalDate;
import jupgo.jupgoserver.domain.diary.Diary;

public class ReturnDiaryDto {
    private long id;
    private LocalDate date;
    private String location;
    private int distance;
    private Duration duration;
    private String photo;

    public ReturnDiaryDto(Diary diary) {
        this.id = diary.getId();
        this.date = diary.getDate();
        this.location = diary.getLocation();
        this.distance = diary.getDistance();
        this.duration = diary.getDuration();
        this.photo = diary.getPhoto();
    }

    public long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public int getDistance() {
        return distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public String getPhoto() {
        return photo;
    }
}
