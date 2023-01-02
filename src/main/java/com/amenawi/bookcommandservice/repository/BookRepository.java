package com.amenawi.bookcommandservice.repository;

import com.amenawi.bookcommandservice.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public
interface BookRepository extends MongoRepository<Book, String> {

    void deleteBookByIsbn(String isbn);
    Book findBookByIsbn(String isbn);

}
