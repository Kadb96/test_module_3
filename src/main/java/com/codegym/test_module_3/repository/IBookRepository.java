package com.codegym.test_module_3.repository;

import com.codegym.test_module_3.model.Book;

import java.util.List;

public interface IBookRepository {
    List<Book> showAll();
    Book show(String id);
    boolean borrowBook(String id);
    boolean returnBook(String id);
}
