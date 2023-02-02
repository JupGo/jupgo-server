package jupgo.jupgoserver.util.response;

public class SuccessResponse<T> {
    private int statusCode;
    private String message;
    private T data;

    public SuccessResponse(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}