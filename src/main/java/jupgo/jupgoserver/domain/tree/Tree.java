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

    private String name;

    private String sort;

    private Integer level;

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
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) { this.sort = sort; }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) { this.level = level; }


    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }
}
