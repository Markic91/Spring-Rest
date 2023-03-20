package com.example.Library.controller;

import com.example.Library.Entity.Book;
import com.example.Library.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
        public List<Book> showAllBooks() {
            return bookRepository.findAll();
        }

        @GetMapping("/books/{id}")
    public Book showById(@PathVariable Long id) {
        return bookRepository.findById((Long)id).get();
        }

        @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
        return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
        }

        @PostMapping("/books")
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
        }

        @PutMapping("/books/{id}")
    public Book update(@PathVariable long id, @RequestBody Book book) {
        Book bookToUpdate = bookRepository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription(book.getDescription());
        return bookRepository.save(bookToUpdate);
        }

        @DeleteMapping("/books/{id}")
    public boolean delete(@PathVariable Long id) {
        bookRepository.deleteById(id);
        return true;
        }
}
