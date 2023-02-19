package jupgo.jupgoserver.dto.tree;


import jupgo.jupgoserver.domain.tree.Tree;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class GetTreeAllResponseDto {

    private Long id;
    private Long distance;
    private List<TreeDto> trees = new ArrayList<>();

    public GetTreeAllResponseDto(List<Tree> trees) {

    }
}
