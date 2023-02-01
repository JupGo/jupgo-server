package jupgo.jupgoserver.domain.tree;

import java.util.List;
import javax.persistence.*;
import jupgo.jupgoserver.domain.diary.Diary;
import jupgo.jupgoserver.domain.user.User;

@Entity
public class Tree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TreeType name;

    private Integer percentage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "tree")
    private List<Diary> diaries;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name.getName();
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }
}
