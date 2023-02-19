package jupgo.jupgoserver.dto.tree;

import java.util.List;
import java.util.stream.Collectors;
import jupgo.jupgoserver.domain.tree.Tree;
import jupgo.jupgoserver.dto.diary.ReturnDiaryDto;

public class ReturnTreeContainDiariesDto {
    private long id;
    private String name;
    private List<ReturnDiaryDto> diaries;

    public ReturnTreeContainDiariesDto(Tree tree) {
        this.id = tree.getId();
        this.name = tree.getName();
        this.diaries = tree.getDiaries().stream()
                .map(ReturnDiaryDto::new)
                .collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ReturnDiaryDto> getDiaries() {
        return diaries;
    }
}
