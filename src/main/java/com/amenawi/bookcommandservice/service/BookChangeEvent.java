package com.amenawi.bookcommandservice.service;

public class BookChangeEvent {
    private String key;
    private BookDto dto;

    public BookChangeEvent(){}
    public BookChangeEvent(String key, BookDto dto){
        this.key = key;
        this.dto = dto;
    }

    public String getKey() {
        return key;
    }

    public BookDto getDto() {
        return dto;
    }
}
