package jupgo.jupgoserver.dto.tree;

import jupgo.jupgoserver.domain.tree.Tree;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TreeDto {

    private Long id;
    private String name;
    private String sort;
    private Integer level;
    private Integer percentage;

    public TreeDto getTrees(Tree tree) {
        this.id = tree.getId();
        this.name = tree.getName();
        this.sort = tree.getSort();
        this.level = tree.getLevel();
        this.percentage = tree.getPercentage();
        return null;
    }
}
