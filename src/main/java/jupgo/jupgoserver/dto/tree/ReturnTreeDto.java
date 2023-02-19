package jupgo.jupgoserver.dto.tree;

import java.util.List;
import java.util.stream.Collectors;
import jupgo.jupgoserver.domain.tree.Tree;
import jupgo.jupgoserver.dto.diary.ReturnDiaryDto;

public class ReturnTreeDto {
    private long id;
    private String name;
    private String sort;
    private int level;
    private int percentage;

    public ReturnTreeDto(Tree tree) {
        this.id = tree.getId();
        this.name = tree.getName();
        this.sort = tree.getSort();
        this.level = tree.getLevel();
        this.percentage = tree.getPercentage();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSort() {
        return sort;
    }

    public int getLevel() {
        return level;
    }

    public int getPercentage() {
        return percentage;
    }
}
