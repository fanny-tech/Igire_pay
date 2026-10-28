package Lab3;

public class InvalidAmountException extends IgirePayException {

    public InvalidAmountException(String message) {
        super(message, "ERR_INVALID_AMOUNT");
    }
}