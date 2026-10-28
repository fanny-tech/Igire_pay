package Lab3;

import java.time.LocalDateTime;

public class IgirePayException extends Exception{
    private String errorCode;
    private LocalDateTime timestamp;

    public IgirePayException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
    }


    public String getErrorCode() {
        return errorCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
