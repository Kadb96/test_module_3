package com.codegym.test_module_3.service;

import com.codegym.test_module_3.model.Book;
import com.codegym.test_module_3.repository.IBookRepository;
import com.codegym.test_module_3.repository.BookRepository;

import java.util.List;

public class BookService implements IBookService {
    IBookRepository bookRepository = new BookRepository();

    @Override
    public List<Book> showAll() {
        return bookRepository.showAll();
    }

    @Override
    public Book show(String id) {
        return bookRepository.show(id);
    }

    @Override
    public boolean borrowBook(String id, int book_quantity) {
        return bookRepository.borrow(id, book_quantity);
    }
}
