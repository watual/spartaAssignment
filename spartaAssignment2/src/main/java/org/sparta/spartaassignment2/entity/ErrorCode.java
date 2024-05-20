package org.sparta.spartaassignment2.entity;

import lombok.Data;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Data
public class ErrorCode {
    private String message;
    private HttpStatus httpStatus;
    private int status;

    public ErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.httpStatus = status;
        this.status = status.value();
    }
}
