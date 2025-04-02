package com.codegym.test_module_3.controller;

import com.codegym.test_module_3.model.Book;
import com.codegym.test_module_3.model.Student;
import com.codegym.test_module_3.service.IBookService;
import com.codegym.test_module_3.service.BookService;
import com.codegym.test_module_3.service.IStudentService;
import com.codegym.test_module_3.service.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "ProductController", value = "/books")
public class BookController extends HttpServlet {
    IBookService bookService = new BookService();
    IStudentService studentService = new StudentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "showAll":
                showAll(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "borrowBook":
                borrowBook(req, resp);
                break;
        }
    }

    private void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.showAll();
        req.setAttribute("books", books);
        req.getRequestDispatcher("view/book.jsp").forward(req, resp);
    }

    private void borrowBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("bookId");
        Book book = bookService.show(bookId);
        req.setAttribute("book", book);

        List<Student> students = studentService.showAll();
        req.setAttribute("students", students);

        LocalDateTime now = LocalDateTime.now();
        req.setAttribute("today", now);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formatedDateNow = now.format(formatter);
        req.setAttribute("formatedDateNow", formatedDateNow);

        req.getRequestDispatcher("view/call_card.jsp").forward(req, resp);
    }
}
