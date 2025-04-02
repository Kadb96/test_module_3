package com.codegym.test_module_3.repository;

import com.codegym.test_module_3.model.Student;

import java.util.List;

public interface IStudentRepository {
    List<Student> showAll();
}
