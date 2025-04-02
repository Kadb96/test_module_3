package com.codegym.test_module_3.controller;

import com.codegym.test_module_3.model.Book;
import com.codegym.test_module_3.model.CallCard;
import com.codegym.test_module_3.repository.CallCardRepository;
import com.codegym.test_module_3.repository.ICallCardRepository;
import com.codegym.test_module_3.service.BookService;
import com.codegym.test_module_3.service.IBookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "CallCardController", value = "/callCards")
public class CallCardController extends HttpServlet {
    ICallCardRepository callCardRepository = new CallCardRepository();
    IBookService bookService = new BookService();

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

    private void borrowBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String callCardId = req.getParameter("callCardId");
        String bookId = req.getParameter("bookId");
        String studentId = req.getParameter("studentId");
        String borrowedAtString = req.getParameter("borrowedAtString");
        String returnedAtString = req.getParameter("returnedAtString");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate borrowedAt = LocalDate.parse(borrowedAtString, formatter);
        LocalDate returnedAt = LocalDate.parse(returnedAtString, formatter);
        CallCard callCard = new CallCard(callCardId, bookId, studentId, borrowedAt, returnedAt);
        callCardRepository.add(callCard);
        bookService.borrowBook(bookId, bookService.show(bookId).getBookQuantity());
        List<Book> books = bookService.showAll();
        req.setAttribute("books", books);
        req.getRequestDispatcher("view/book.jsp").forward(req, resp);
    }
}
