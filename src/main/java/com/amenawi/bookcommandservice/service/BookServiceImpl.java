package com.amenawi.bookcommandservice.service;

import com.amenawi.bookcommandservice.integration.KafkaProducer;
import com.amenawi.bookcommandservice.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.amenawi.bookcommandservice.domain.Book;
@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private KafkaProducer kafkaProducer;
    @Override
    public void addBook(BookDto dto) {
        bookRepository.save(BookAdapter.fromDto(dto));
        try{
            ObjectMapper mapper = new ObjectMapper();
            BookChangeEvent  event = new BookChangeEvent("add", dto);
            String bk = mapper.writeValueAsString(event);
            kafkaProducer.send("bookstopic", bk);
        } catch (Exception e) {
           e.printStackTrace();
        }

    }

    @Override
    public void deleteBook(String isbn) {
        Book book = bookRepository.findBookByIsbn(isbn);
        bookRepository.deleteBookByIsbn(isbn);

        BookDto  dto = BookAdapter.toDto(book);

        try{
            ObjectMapper mapper = new ObjectMapper();
            BookChangeEvent  even = new BookChangeEvent("delete", dto);
            String deleted = mapper.writeValueAsString(even);
            kafkaProducer.send("bookstopic", deleted);
        }catch (Exception ex){
            ex.printStackTrace();
        }


    }

    @Override
    public void updateBook(String isbn, BookDto dto) {

        Book book = bookRepository.findBookByIsbn(isbn);
        if(book != null){
            Book other = BookAdapter.fromDto(dto);
            bookRepository.save(other);

            try {
                ObjectMapper mapper = new ObjectMapper();
                BookChangeEvent event = new BookChangeEvent("update", dto);
                String result = mapper.writeValueAsString(event);
                kafkaProducer.send("bookstopic", result);

            }catch (Exception ex){
                ex.printStackTrace();
            }

        }

    }

    @Override
    public BookDto getBookByIsbn(String isbn) {
        Book book = bookRepository.findBookByIsbn(isbn);

        return BookAdapter.toDto(book);
    }
}
