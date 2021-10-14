package cn.imessay.speedtest.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
    public UserNotFoundException() {
        super();
    }
}
