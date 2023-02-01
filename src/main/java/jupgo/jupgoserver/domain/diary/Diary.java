package jupgo.jupgoserver.domain.diary;

import jupgo.jupgoserver.domain.tree.Tree;
import jupgo.jupgoserver.domain.user.User;
import org.hibernate.type.DurationType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Diary {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="diary_id")
    private Tree tree;

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

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public DurationType getDuration() {
        return duration;
    }

    public void setDuration(DurationType duration) {
        this.duration = duration;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    private String location;
    private Float distance;
    private DurationType duration;
    private String photo;

}