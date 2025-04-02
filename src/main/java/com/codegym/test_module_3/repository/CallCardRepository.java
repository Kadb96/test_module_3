package com.codegym.test_module_3.repository;

import com.codegym.test_module_3.dto.CallCardDto;
import com.codegym.test_module_3.model.CallCard;
import com.codegym.test_module_3.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CallCardRepository implements ICallCardRepository {
    private final String SHOW_ALL = "SELECT cc.*, b.book_name, b.book_author, s.student_name, s.student_class FROM call_card cc JOIN book b ON cc.book_id = b.book_id JOIN student s ON cc.student_id = s.student_id WHERE cc.is_borrowing = 1";
    private final String SEARCH = "SELECT cc.*, b.book_name, b.book_author, s.student_name, s.student_class FROM call_card cc JOIN book b ON cc.book_id = b.book_id JOIN student s ON cc.student_id = s.student_id WHERE cc.is_borrowing = 1 AND book_name LIKE ? AND student_name LIKE ?";
    private final String ADD = "INSERT INTO call_card(call_card_id, book_id, student_id, borrowed_at, returned_at) VALUES (?,?,?,?,?)";
    private final String RETURN_BOOK = "UPDATE call_card SET is_borrowing = 0 WHERE call_card_id = ?";

    @Override
    public List<CallCardDto> showAll() {
        List<CallCardDto> callCards = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement statement = connection.prepareStatement(SHOW_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String callCardId = resultSet.getString("call_card_id");
                String bookId = resultSet.getString("book_id");
                String studentId = resultSet.getString("student_id");
                boolean isBorrowing = resultSet.getBoolean("is_borrowing");
                LocalDate borrowedAt = resultSet.getDate("borrowed_at").toLocalDate();
                LocalDate returnedAt = resultSet.getDate("returned_at").toLocalDate();
                String bookName = resultSet.getString("book_name");
                String studentName = resultSet.getString("student_name");
                String studentClass = resultSet.getString("student_class");
                String bookAuthor = resultSet.getString("book_author");

                callCards.add(new CallCardDto(callCardId, bookId, studentId, isBorrowing, borrowedAt, returnedAt, bookName, studentName, studentClass, bookAuthor));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return callCards;
    }

    @Override
    public List<CallCardDto> search(String keywordBookName, String keywordStudentName) {
        List<CallCardDto> callCards = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement statement = connection.prepareStatement(SEARCH);
            statement.setString(1,"%" + keywordBookName + "%");
            statement.setString(2, "%" + keywordStudentName + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String callCardId = resultSet.getString("call_card_id");
                String bookId = resultSet.getString("book_id");
                String studentId = resultSet.getString("student_id");
                boolean isBorrowing = resultSet.getBoolean("is_borrowing");
                LocalDate borrowedAt = resultSet.getDate("borrowed_at").toLocalDate();
                LocalDate returnedAt = resultSet.getDate("returned_at").toLocalDate();
                String bookName = resultSet.getString("book_name");
                String studentName = resultSet.getString("student_name");
                String studentClass = resultSet.getString("student_class");
                String bookAuthor = resultSet.getString("book_author");

                callCards.add(new CallCardDto(callCardId, bookId, studentId, isBorrowing, borrowedAt, returnedAt, bookName, studentName, studentClass, bookAuthor));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return callCards;
    }

    @Override
    public boolean add(CallCard callCard) {
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement statement = connection.prepareStatement(ADD);
            statement.setString(1, callCard.getCallCardId());
            statement.setString(2, callCard.getBookId());
            statement.setString(3, callCard.getStudentId());
            statement.setString(4, callCard.getBorrowedAtStringSql());
            statement.setString(5, callCard.getReturnedAtStringSql());
            if(statement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean returnBook(String callCardId) {
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement statement = connection.prepareStatement(RETURN_BOOK);
            statement.setString(1, callCardId);
            if(statement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
