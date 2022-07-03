package testing.satrio.backend.payload.response;

public class XMessageResponse {
    private String message;

    public XMessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
