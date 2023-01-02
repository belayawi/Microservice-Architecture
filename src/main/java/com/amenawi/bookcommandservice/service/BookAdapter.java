package com.amenawi.bookcommandservice.service;

import com.amenawi.bookcommandservice.domain.Book;

public class BookAdapter {

    public static Book fromDto(BookDto dto){
        return new Book(dto.getIsbn(), dto.getTitle(), dto.getDescription(), dto.getAuthorName());
    }
    public static BookDto toDto(Book book){
        return new BookDto(book.getIsbn(), book.getTitle(), book.getDescription(), book.getAuthorName());
    }
}
