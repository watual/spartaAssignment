package org.sparta.spartaassignment2.error;

public class WrongWayException extends RuntimeException{
    public WrongWayException(String message) { super(message); }
    public WrongWayException(String message, Throwable cause) {
        super(message, cause);
    }
}
