package com.codegym.test_module_3.repository;

import com.codegym.test_module_3.model.Book;
import com.codegym.test_module_3.model.Student;
import com.codegym.test_module_3.util.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IStudentRepository {
    private final String SHOW_ALL = "SELECT * FROM student";

    @Override
    public List<Student> showAll() {
        List<Student> students = new ArrayList<>();
        Connection connection = BaseRepository.getConnectDB();
        try {
            PreparedStatement statement = connection.prepareStatement(SHOW_ALL);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String studentId = resultSet.getString("student_id");
                String studentName = resultSet.getString("student_name");
                String studentClass = resultSet.getString("student_class");
                students.add(new Student(studentId, studentName, studentClass));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return students;
    }
}
