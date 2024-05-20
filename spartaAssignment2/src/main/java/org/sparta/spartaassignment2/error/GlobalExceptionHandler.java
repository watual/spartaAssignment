package org.sparta.spartaassignment2.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sparta.spartaassignment2.entity.ErrorCode;
import org.sparta.spartaassignment2.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice   // 전역 설정 Handler
public class GlobalExceptionHandler {
    private final Logger logger;

    public GlobalExceptionHandler() {
        this.logger = LoggerFactory.getLogger(GlobalExceptionHandler.class.getName());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<String> handleFileStorageException(FileStorageException ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ErrorCode handleWrongPasswordException(WrongPasswordException ex) {
        logger.error(ex.getMessage(), ex);
        return new ErrorCode("비밀번호를 틀렸습니다.", HttpStatus.valueOf(455));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> handleIOException(IOException ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
