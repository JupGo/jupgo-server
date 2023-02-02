package jupgo.jupgoserver.util.response;

public enum StatusCode {
    OK(200),
    CREATED(201);

    private final int code;

    StatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
