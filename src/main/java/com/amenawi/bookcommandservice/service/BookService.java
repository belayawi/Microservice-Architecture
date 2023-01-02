package com.amenawi.bookcommandservice.service;

public interface BookService {
    void addBook(BookDto book);
    void deleteBook(String isbn);
    void updateBook(String isbn, BookDto dto);
    BookDto getBookByIsbn(String isbn);

}
