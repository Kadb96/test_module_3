package com.codegym.test_module_3.repository;

import com.codegym.test_module_3.model.Book;
import com.codegym.test_module_3.model.CallCard;
import com.codegym.test_module_3.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CallCardRepository implements ICallCardRepository {
    private final String SHOW_ALL = "SELECT * FROM call_card";
    private final String ADD = "INSERT INTO call_card VALUES (?,?,?,?,?)";

    @Override
    public List<CallCard> showAll() {
        List<CallCard> callCards = new ArrayList<>();
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

                callCards.add(new CallCard(callCardId, bookId, studentId, isBorrowing,borrowedAt, returnedAt));
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
            statement.setDate(4, java.sql.Date.valueOf(String.valueOf(callCard.getBorrowedAt())));
            statement.setDate(5, java.sql.Date.valueOf(String.valueOf(callCard.getReturnedAt())));
            statement.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
