package net.cloudcentrik.beeforder.Controller;

import net.cloudcentrik.beeforder.dto.BookRequestDTO;
import net.cloudcentrik.beeforder.dto.BookUpdateRequestDTO;
import net.cloudcentrik.beeforder.model.Book;
import net.cloudcentrik.beeforder.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping
    public Book createNew(@RequestBody BookRequestDTO bookRequestDTO){
        return bookService.createNew(bookRequestDTO);
    }

    @GetMapping
    public Book getById(@RequestParam String bookId){
        return bookService.getById(bookId);
    }

    @GetMapping("getByName")
    public List<Book> getByName(@RequestParam String name){
        return bookService.getByName(name);
    }

    @GetMapping("getByAuthor")
    public List<Book> getByAuthor(@RequestParam String authorName){
        return bookService.getByAuthor(authorName);
    }

    @PutMapping
    public Book updateBook(@RequestBody BookUpdateRequestDTO bookUpdateRequestDTO){
        return bookService.updateBook(bookUpdateRequestDTO);
    }

    @DeleteMapping
    public Boolean deleteBook(@RequestParam String bookId){
        return bookService.deleteById(bookId);
    }

}
