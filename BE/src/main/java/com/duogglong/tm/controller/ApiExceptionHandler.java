package com.duogglong.tm.controller;

import com.duogglong.tm.dto.SampleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiExceptionHandler {
    /**
     * Tất cả các Exception không được khai báo sẽ được xử lý tại đây
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<SampleResponse<String>> handleAllException(Exception ex, WebRequest request) {
        // quá trình kiểm soat lỗi diễn ra ở đây
        return new ResponseEntity<>(new SampleResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getLocalizedMessage(), null), HttpStatus.BAD_REQUEST);
    }

    /**
     * IndexOutOfBoundsException sẽ được xử lý riêng tại đây
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<SampleResponse<String>> TodoException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new SampleResponse<>(HttpStatus.BAD_REQUEST.value(), "Object does not exits", null), HttpStatus.BAD_REQUEST);
    }
}
