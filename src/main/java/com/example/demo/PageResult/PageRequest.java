package com.example.demo.PageResult;

import org.springframework.data.domain.Sort;

import java.util.List;


/**
 * 分页返回结果
 */
public class PageRequest extends org.springframework.data.domain.PageRequest {
    Sort by = Sort.by("uuid");

    public PageRequest(int page, int size) {
        super(page, size, Sort.unsorted());
       // PageRequest.of(page, size);

    }
}
