package com.codegym.test_module_3.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CallCardDto {
    private String callCardId;
    private String bookId;
    private String studentId;
    private boolean isBorrowing;
    private LocalDate borrowedAt;
    private LocalDate returnedAt;
    private String bookName;
    private String studentName;
    private String studentClass;
    private String bookAuthor;

    public CallCardDto(String callCardId, String bookId, String studentId, boolean isBorrowing, LocalDate borrowedAt, LocalDate returnedAt, String bookName, String studentName, String studentClass, String bookAuthor) {
        this.callCardId = callCardId;
        this.bookId = bookId;
        this.studentId = studentId;
        this.isBorrowing = isBorrowing;
        this.borrowedAt = borrowedAt;
        this.returnedAt = returnedAt;
        this.bookName = bookName;
        this.studentName = studentName;
        this.studentClass = studentClass;
        this.bookAuthor = bookAuthor;
    }

    public CallCardDto() {
    }

    public CallCardDto(String callCardId, String bookId, String studentId, boolean isBorrowing, LocalDate borrowedAt, LocalDate returnedAt) {
        this.callCardId = callCardId;
        this.bookId = bookId;
        this.studentId = studentId;
        this.isBorrowing = isBorrowing;
        this.borrowedAt = borrowedAt;
        this.returnedAt = returnedAt;
    }

    public CallCardDto(String callCardId, String bookId, String studentId, LocalDate borrowedAt, LocalDate returnedAt) {
        this.callCardId = callCardId;
        this.bookId = bookId;
        this.studentId = studentId;
        this.borrowedAt = borrowedAt;
        this.returnedAt = returnedAt;
    }

    public String getCallCardId() {
        return callCardId;
    }

    public void setCallCardId(String callCardId) {
        this.callCardId = callCardId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public boolean isIsBorrowing() {
        return isBorrowing;
    }

    public void setIsBorrowing(boolean is_borrowing) {
        this.isBorrowing = is_borrowing;
    }

    public LocalDate getBorrowedAt() {
        return borrowedAt;
    }

    public void setBorrowedAt(LocalDate borrowedAt) {
        this.borrowedAt = borrowedAt;
    }

    public LocalDate getReturnedAt() {
        return returnedAt;
    }

    public void setReturnedAt(LocalDate returnedAt) {
        this.returnedAt = returnedAt;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBorrowedAtStringSql() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String borrowedAtString = borrowedAt.format(formatter);
        return borrowedAtString;
    }

    public String getBorrowedAtString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String borrowedAtString = borrowedAt.format(formatter);
        return borrowedAtString;
    }

    public String getReturnedAtStringSql() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String returnedAtString = returnedAt.format(formatter);
        return returnedAtString;
    }
    public String getReturnedAtString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String returnedAtString = returnedAt.format(formatter);
        return returnedAtString;
    }
}
