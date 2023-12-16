package net.cloudcentrik.beeforder.service;


import net.cloudcentrik.beeforder.dto.BookRequestDTO;
import net.cloudcentrik.beeforder.dto.BookUpdateRequestDTO;
import net.cloudcentrik.beeforder.model.Book;

import java.util.List;

public interface BookService {

    Book createNew(BookRequestDTO bookRequestDTO);

    Book updateBook(BookUpdateRequestDTO bookUpdateRequestDTO);

    Boolean deleteById(String id);

    List<Book> getByAuthor(String author);

    List<Book> getByName(String name);

    Book getById(String bookId);

}