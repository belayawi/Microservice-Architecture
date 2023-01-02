package com.amenawi.bookcommandservice.controller;

import com.amenawi.bookcommandservice.service.BookDto;
import com.amenawi.bookcommandservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    private BookService bookService;

    @PostMapping("/books")
    public ResponseEntity<?> addBook(@RequestBody BookDto dto){
        bookService.addBook(dto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @PutMapping("/books/{isbn}")
    public ResponseEntity<?> updateBook(@PathVariable String isbn, @RequestBody BookDto dto){
        bookService.updateBook(isbn, dto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/books/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn){
        bookService.deleteBook(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/books/{isbn}")
    public ResponseEntity<?> getBook(@PathVariable String isbn){
        BookDto dto = bookService.getBookByIsbn(isbn);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
