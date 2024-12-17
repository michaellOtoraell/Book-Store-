package com.otorael.phone_dealer.ResponseDTO;

/**
 * Data Transfer Object (DTO) class for a book.
 * Used for transferring book-related data including a message to the client.
 */
public class BooksDTO {

    private String author;
    private String bookName;
    private String publishYear;
    private String publisher;
    private String price;
    private String message;

    /**
     * Constructor to create a BooksDTO with all fields.
     *
     * @param author The author of the book.
     * @param bookName The name of the book.
     * @param publishYear The year the book was published.
     * @param publisher The publisher of the book.
     * @param price The price of the book.
     * @param message Additional message associated with the book data.
     */
    public BooksDTO(String author, String bookName, String publishYear, String publisher, String price, String message) {
        this.author = author;
        this.bookName = bookName;
        this.publishYear = publishYear;
        this.publisher = publisher;
        this.price = price;
        this.message = message;
    }

    /**
     * Gets the author of the book.
     *
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     *
     * @param author The author to set for the book.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the name of the book.
     *
     * @return The name of the book.
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * Sets the name of the book.
     *
     * @param bookName The name to set for the book.
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * Gets the year the book was published.
     *
     * @return The year the book was published.
     */
    public String getPublishYear() {
        return publishYear;
    }

    /**
     * Sets the year the book was published.
     *
     * @param publishYear The publish year to set for the book.
     */
    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    /**
     * Gets the publisher of the book.
     *
     * @return The publisher of the book.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets the publisher of the book.
     *
     * @param publisher The publisher to set for the book.
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Gets the price of the book.
     *
     * @return The price of the book.
     */
    public String getPrice() {
        return price;
    }

    /**
     * Sets the price of the book.
     *
     * @param price The price to set for the book.
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * Gets any additional message associated with the book.
     *
     * @return The message associated with the book.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the additional message associated with the book.
     *
     * @param message The message to set for the book.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
