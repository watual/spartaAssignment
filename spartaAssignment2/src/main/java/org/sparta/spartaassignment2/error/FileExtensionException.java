package org.sparta.spartaassignment2.error;

public class FileExtensionException extends RuntimeException{
    public FileExtensionException(String message) {
        super(message);
    }
    public FileExtensionException(String message, Throwable cause) {
        super(message, cause);
    }
}
