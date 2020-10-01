package com.eurovision.response;

import java.util.List;

import com.eurovision.model.City;


public class CustomHttpResponse {

    private List<City> content;
    private Integer totalPages;
    private Long totalElements;
    private Boolean last;
    private Integer size;
    private Integer number;

    public CustomHttpResponse(List<City> pContent, Integer pTotalPages, Long pTotalElements, Boolean pLast , Integer pSize, Integer pNumber){
        content = pContent;
        totalPages = pTotalPages;
        totalElements = pTotalElements;
        last = pLast;
        size = pSize;
        number = pNumber;
    }

    public List<City> getContent() {
        return content;
    }



    public Integer getTotalPages() {
        return totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public Boolean getLast() {
        return last;
    }

    public Integer getSize() {
        return size;
    }

    public Integer getNumber() {
        return number;
    }
}
