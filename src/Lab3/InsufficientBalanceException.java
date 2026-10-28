package Lab3;

public class InsufficientBalanceException extends IgirePayException {

    public InsufficientBalanceException(String message) {

        super(message, "ERR_INSUFFICIENT_BALANCE");
    }
}

