package org.sparta.spartaassignment2.error;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String message) { super(message); }
    public WrongPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
