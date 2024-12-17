package com.otorael.phone_dealer.ResponseDTO;

public class BooksDTO {

    private String author;
    private String bookName;
    private String publishYear;
    private String publisher;
    private String price;
    private String message;

    public BooksDTO(String author, String bookName, String publishYear, String publisher, String price, String message) {
        this.author = author;
        this.bookName = bookName;
        this.publishYear = publishYear;
        this.publisher = publisher;
        this.price = price;
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
