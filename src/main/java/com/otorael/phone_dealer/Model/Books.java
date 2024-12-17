package com.otorael.phone_dealer.Model;

import jakarta.persistence.*;

/**
 * Represents a book entity in the database.
 * This class corresponds to the "books" table in the database.
 */
@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;
    private String bookName;
    private String publishYear;
    private String publisher;
    private String price;

    /**
     * Constructor for creating a Book with specific values.
     *
     * @param id The ID of the book.
     * @param author The author of the book.
     * @param bookName The name of the book.
     * @param publishYear The year the book was published.
     * @param publisher The publisher of the book.
     * @param price The price of the book.
     */
    public Books(Long id, String author, String bookName, String publishYear, String publisher, String price) {
        this.id = id;
        this.author = author;
        this.bookName = bookName;
        this.publishYear = publishYear;
        this.publisher = publisher;
        this.price = price;
    }

    /**
     * Default constructor for creating a new instance of Books.
     */
    public Books() {
    }

    /**
     * Gets the ID of the book.
     *
     * @return The ID of the book.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the book.
     *
     * @param id The ID to set for the book.
     */
    public void setId(Long id) {
        this.id = id;
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
     * Gets the publish year of the book.
     *
     * @return The publish year of the book.
     */
    public String getPublishYear() {
        return publishYear;
    }

    /**
     * Sets the publish year of the book.
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
}
