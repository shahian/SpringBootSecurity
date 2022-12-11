package com.shahian.springBootSecurity.servic;

import com.shahian.springBootSecurity.model.Book;
import com.shahian.springBootSecurity.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class BookService {
    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public Book getPersonById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new NullPointerException("this is null"));
    }

    public List<Book> getAllPerson() {
        return bookRepository.findAll();
    }

    @Transactional
    public void addBook(Book book) {
        Book book1 = Book.builder().name(book.getName()).price(book.getPrice()).build();
        bookRepository.save(book1);

    }

    @Transactional
    public Book updateBook(Long id, Book book) {
        Book obj = bookRepository.findById(id).orElseThrow(() -> new NullPointerException("this is null"));
        obj.setName(book.getName());
        obj.setPrice(book.getPrice());

        bookRepository.save(obj);
        return obj;
    }

    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new NullPointerException("this is null"));
        bookRepository.delete(book);
    }
}
