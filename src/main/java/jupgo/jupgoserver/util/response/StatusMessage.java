package jupgo.jupgoserver.util.response;

public enum StatusMessage {

    // Diary
    PLOGGING_SAVE_SUCCESS("플로깅 기록 저장 성공");

    private final String message;

    StatusMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
