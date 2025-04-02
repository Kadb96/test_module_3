package com.codegym.test_module_3.model;

public class Book {
    private String bookId;
    private String bookName;
    private String bookAuthor;
    private String bookDescription;
    private int bookQuantity;

    public Book(String id, String bookName, String bookAuthor, String bookDescription, int bookQuantity) {
        this.bookId = id;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookDescription = bookDescription;
        this.bookQuantity = bookQuantity;
    }

    public Book() {
    }

    public String getProductPriceToString() {
        int price = bookQuantity;
        String priceToString = String.format("%,d", price);
        return priceToString;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String id) {
        this.bookId = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public int getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }
}
