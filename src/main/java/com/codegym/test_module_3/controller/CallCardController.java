package com.codegym.test_module_3.controller;

import com.codegym.test_module_3.dto.CallCardDto;
import com.codegym.test_module_3.model.Book;
import com.codegym.test_module_3.model.CallCard;
import com.codegym.test_module_3.service.BookService;
import com.codegym.test_module_3.service.CallCardService;
import com.codegym.test_module_3.service.IBookService;
import com.codegym.test_module_3.service.ICallCardService;

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
    ICallCardService callCardService = new CallCardService();
    IBookService bookService = new BookService();


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
            case "returnBook":
                returnBook(req, resp);
                break;
            case "search":
                searchCallCard(req, resp);
                break;
        }
    }

    private void searchCallCard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookName = req.getParameter("bookName");
        String studentName = req.getParameter("studentName");
        List<CallCardDto> callCards = callCardService.search(bookName, studentName);
        req.setAttribute("callCards", callCards);
        req.getRequestDispatcher("view/borrowed_book.jsp").forward(req, resp);
    }

    private void showAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CallCardDto> callCards = callCardService.showAll();
        req.setAttribute("callCards", callCards);
        req.getRequestDispatcher("/view/borrowed_book.jsp").forward(req, resp);
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
        if (callCardService.add(callCard)) {
            bookService.borrowBook(bookId);
        }
        List<Book> books = bookService.showAll();
        req.setAttribute("books", books);
        req.getRequestDispatcher("view/book.jsp").forward(req, resp);
    }

    private void returnBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String callCardId = req.getParameter("callCardId");
        String bookId = req.getParameter("bookId");
        if (callCardService.returnBook(callCardId)) {
            bookService.returnBook(bookId);
        }
        showAll(req, resp);
    }
}
