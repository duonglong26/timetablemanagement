package com.duogglong.tm.core.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SampleResponse<T> {
    private int status;
    private String message;
    private T data;

    public SampleResponse() {
    }

    public SampleResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}
