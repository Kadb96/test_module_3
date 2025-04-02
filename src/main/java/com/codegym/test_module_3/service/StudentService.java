package com.codegym.test_module_3.service;

import com.codegym.test_module_3.model.Student;
import com.codegym.test_module_3.repository.IStudentRepository;
import com.codegym.test_module_3.repository.StudentRepository;

import java.util.List;

public class StudentService implements IStudentService{
    IStudentRepository studentRepository = new StudentRepository();
    @Override
    public List<Student> showAll() {
        return studentRepository.showAll();
    }
}
