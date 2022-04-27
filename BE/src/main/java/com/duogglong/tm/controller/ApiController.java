package com.duogglong.tm.controller;

import com.duogglong.tm.dto.SampleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface ApiController<T> {
    ResponseEntity<SampleResponse<T>> create(T dto);
    ResponseEntity<SampleResponse<T>> update(T dto);
    ResponseEntity<SampleResponse<T>> getById(@PathVariable("id") String id);
    ResponseEntity<SampleResponse<Boolean>> delete(@PathVariable("id") String id);
}
