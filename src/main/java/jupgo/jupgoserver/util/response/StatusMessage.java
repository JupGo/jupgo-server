package jupgo.jupgoserver.util.response;

public enum StatusMessage {

    // Diary
    PLOGGING_SAVE_SUCCESS("플로깅 기록 저장 성공"),
    GET_DIARIES_OF_TREE_SUCCESS("특정 나무의 기록 조회 성공"),
    GET_ALL_DIARIES("모든 플로깅 기록 조회 성공"),

    SIGNUP_SUCCESS("회원가입 성공"),
    LOGIN_SUCCESS("로그인 성공"),

    LOGIN_FAIL("로그인 실패"),
    UNAUTHORIZED("유효하지 않은 토큰입니다."),
    NOT_OWNER_OF_TREE("로그인 한 유저의 나무가 아닙니다."),
    NOT_EXIST_TREE("존재하지 않는 나무의 id입니다."),
    INTERNAL_ERROR("서버 내부 오류");

    private final String message;

    StatusMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
