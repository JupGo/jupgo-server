package jupgo.jupgoserver.domain.tree;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import jupgo.jupgoserver.domain.diary.Diary;
import jupgo.jupgoserver.domain.user.User;

@Entity
public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String sort;

    private Integer level;

    private Integer percentage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "tree")
    private List<Diary> diaries;

    public Tree(User user) {
        this.user = user;
        this.name = TreeType.generateName();
        this.sort = TreeType.generateSort();
        this.level = 1;
        this.percentage = 0;
        this.diaries = new ArrayList<>();
    }

    public Tree() {
        this.name = TreeType.generateName();
        this.sort = TreeType.generateSort();
        this.level = 1;
        this.percentage = 0;
        this.diaries = new ArrayList<>();
    }

    public void addExperience(int distance, Duration duration) {
        int experienceOfDistance = convertDistanceToExperience(distance);
        int experienceOfDuration = convertDistanceToExperience(duration);
        ExperienceManager.addExperience(this, experienceOfDistance + experienceOfDuration);
    }

    private int convertDistanceToExperience(int distance) {
        return distance;
    }

    private int convertDistanceToExperience(Duration duration) {
        return (int) (duration.getSeconds() / 60) * 100;

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public String getSort() {
        return sort;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public User getUser() {
        return user;
    }

    public List<Diary> getDiaries() {
        return diaries;
    }
}
