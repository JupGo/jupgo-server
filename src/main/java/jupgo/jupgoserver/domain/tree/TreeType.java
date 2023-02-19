package jupgo.jupgoserver.domain.tree;

import java.util.List;
import java.util.Random;

public class TreeType {
    private static final List<String> NAMES = List.of("열정나무", "스피드나무", "러닝나무", "초록나무", "이삭나무", "에코나무", "탄소나무", "산소나무",
            "청정나무", "뿌듯나무", "무럭나무", "성장나무", "기쁨나무", "달려나무", "행복나무", "최고나무", "희망나무", "너랑나무", "줍줍나무", "노래나무", "미소나무",
            "웃음나무", "건강나무", "튼튼나무", "꿈꾸는나무", "에너지나무", "나무나무", "쑥쑥나무", "활짝나무", "힘쎈나무");
    private static final List<String> SORTS = List.of("first", "second");
    private static final List<Integer> LEVELS = List.of(0, 1, 2, 3, 4, 5);

    public static String generateName() {
        Random random = new Random();
        return NAMES.get(random.nextInt(NAMES.size()));
    }

    public static String generateSort() {
        Random random = new Random();
        return SORTS.get(random.nextInt(SORTS.size()));
    }
}
