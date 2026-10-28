package Lab3;

public class AccountNotFoundException extends IgirePayException {

    public void InvalidAmountException(String message) // (Cyangwa AccountNotFoundException)
    {

    }

    public AccountNotFoundException(String message) {
        super(message, "ERR_ACCOUNT_NOT_FOUND");
    }
}