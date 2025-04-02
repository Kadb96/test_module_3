package com.codegym.test_module_3.model;

import jdk.vm.ci.meta.Local;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class CallCard {
    private String callCardId;
    private String bookId;
    private String studentId;
    private boolean isBorrowing;
    private LocalDate borrowedAt;
    private LocalDate returnedAt;

    public CallCard() {
    }

    public CallCard(String callCardId, String bookId, String studentId, boolean isBorrowing, LocalDate borrowedAt, LocalDate returnedAt) {
        this.callCardId = callCardId;
        this.bookId = bookId;
        this.studentId = studentId;
        this.isBorrowing = isBorrowing;
        this.borrowedAt = borrowedAt;
        this.returnedAt = returnedAt;
    }

    public CallCard(String callCardId, String bookId, String studentId, LocalDate borrowedAt, LocalDate returnedAt) {
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
}
