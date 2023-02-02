package jupgo.jupgoserver.domain.diary;

import java.time.Duration;
import jupgo.jupgoserver.domain.tree.Tree;
import jupgo.jupgoserver.dto.DiaryRequestDto;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Diary {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private String location;

    private int distance;

    private Duration duration;

    private String photo;

    @ManyToOne
    @JoinColumn(name="tree_id")
    private Tree tree;

    public Diary(DiaryRequestDto diaryRequestDto) {
        this.date = diaryRequestDto.getDate();
        this.location = diaryRequestDto.getLocation();
        this.distance = diaryRequestDto.getDistance();
        this.duration = diaryRequestDto.getDuration();
        this.photo = diaryRequestDto.getPhoto();
    }

    public Diary() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}