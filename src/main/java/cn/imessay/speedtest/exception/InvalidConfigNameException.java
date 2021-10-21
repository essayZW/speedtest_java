package cn.imessay.speedtest.exception;

public class InvalidConfigNameException extends Exception {
    public InvalidConfigNameException(String message) {
        super(message);
    }

    public InvalidConfigNameException() {
        super();
    }
}
