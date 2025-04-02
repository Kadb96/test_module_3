package com.codegym.test_module_3.repository;

import com.codegym.test_module_3.model.Book;
import com.codegym.test_module_3.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements IBookRepository {
    private final String SHOW_ALL = "SELECT * FROM book";
    private final String SHOW_BY_ID = "SELECT * FROM book WHERE book_id = ?";
    private final String BORROW_BOOK = "UPDATE book SET book_quantity = book_quantity - 1 WHERE book_id = ?";
    private final String RETURN_BOOK = "UPDATE book SET book_quantity = book_quantity + 1 WHERE book_id = ?";

    @Override
    public List<Book> showAll() {
        List<Book> books = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement statement = connection.prepareStatement(SHOW_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String bookId = resultSet.getString("book_id");
                String bookName = resultSet.getString("book_name");
                String bookAuthor = resultSet.getString("book_author");
                String bookDescription = resultSet.getString("book_description");
                int bookQuantity = resultSet.getInt("book_quantity");
                books.add(new Book(bookId, bookName, bookAuthor, bookDescription, bookQuantity));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }

    @Override
    public Book show(String id) {
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement statement = connection.prepareStatement(SHOW_BY_ID);
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String bookId = resultSet.getString("book_id");
                String bookName = resultSet.getString("book_name");
                String bookAuthor = resultSet.getString("book_author");
                String bookDescription = resultSet.getString("book_description");
                int bookQuantity = resultSet.getInt("book_quantity");
                return new Book(bookId, bookName, bookAuthor, bookDescription, bookQuantity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean borrowBook(String id) {
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement statement = connection.prepareStatement(BORROW_BOOK);
            statement.setString(1, id);
            if (statement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean returnBook(String id) {
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement statement = connection.prepareStatement(RETURN_BOOK);
            statement.setString(1, id);
            if (statement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}