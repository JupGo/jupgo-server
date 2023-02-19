package jupgo.jupgoserver.util.response;

public enum StatusCode {
    OK(200),
    CREATED(201),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_ERROR(500);


    private final int code;

    StatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
