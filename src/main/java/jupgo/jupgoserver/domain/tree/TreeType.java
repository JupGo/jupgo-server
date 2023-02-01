package jupgo.jupgoserver.domain.tree;

public enum TreeType {
    PASSION_TREE("열정나무"),
    SPEED_TREE("스피드나무"),
    RUNNING_TREE("러닝나무"),
    GREEN_TREE("초록나무"),
    EAR_TREE("이삭나무"),
    ECO_TREE("에코나무"),
    CARBON_TREE("탄소나무"),
    OXYGEN_TREE("산소나무"),
    CLEAN_TREE("청정나무"),
    PROUD_TREE("뿌듯나무"),
    MOOREOK_TREE("무럭나무"),
    GROWTH_TREE("성장나무"),
    JOY_TREE("기쁨나무"),
    RUN_TREE("달려나무"),
    HAPPINESS_TREE("행복나무"),
    BEST_TREE("최고나무"),
    HOPE_TREE("희망나무"),
    WITHYOU_TREE("너랑나무"),
    JUPJUP_TREE("줍줍나무"),
    SONG_TREE("노래나무"),
    SMILE_TREE("미소나무"),
    LAUGH_TREE("웃음나무"),
    HEALTH_TREE("건강나무"),
    TOUGH_TREE("튼튼나무"),
    DREAMING_TREE("꿈꾸는나무"),
    ENERGY_TREE("에너지나무"),
    TREE_TREE("나무나무"),
    SSOOKSSOOK_TREE("쑥쑥나무"),
    HWALJJAK_TREE("활짝나무"),
    STRONG_TREE("힘쎈나무");

    private String name;

    TreeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
