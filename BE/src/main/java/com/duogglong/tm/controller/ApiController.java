package com.duogglong.tm.controller;

import com.duogglong.tm.core.entity.SampleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ApiController<T> {
    ResponseEntity<SampleResponse<T>> createOrUpdate(@RequestBody  T dto);
    ResponseEntity<SampleResponse<T>> getById(@PathVariable("id") String id);
    ResponseEntity<SampleResponse<Boolean>> delete(@PathVariable("id") String id);
}
