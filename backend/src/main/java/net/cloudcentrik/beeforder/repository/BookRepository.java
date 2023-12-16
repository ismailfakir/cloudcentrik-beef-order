package net.cloudcentrik.beeforder.repository;

import net.cloudcentrik.beeforder.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    List<Book> findByName(String name);

    List<Book> findByAuthor(String authorName);

}