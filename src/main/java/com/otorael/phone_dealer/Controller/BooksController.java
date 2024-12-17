package com.otorael.phone_dealer.Controller;

import com.otorael.phone_dealer.Model.Books;
import com.otorael.phone_dealer.ResponseDTO.BooksDTO;
import com.otorael.phone_dealer.ResponseDTO.messageDTO;
import com.otorael.phone_dealer.Services.BooksService.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for managing book operations in the application.
 * Contains endpoints for adding, retrieving, and listing books.
 */
@RestController
@RequestMapping("/api/v1/")
public class BooksController {

    /**
     * Autowired service for handling business logic related to books.
     */
    @Autowired
    private final BooksService booksService;

    /**
     * Constructor for injecting the BooksService.
     * @param booksService service for managing books.
     */
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    /**
     * Adds a new book to the system.
     * Endpoint: POST /protected/add-book
     * @param books book details sent in the request body.
     * @return ResponseEntity containing the status and response message.
     */
    @RequestMapping(value = "/protected/add-book", method = RequestMethod.POST)
    public ResponseEntity<?> addBook(@RequestBody  Books books){

        // Add the book to the system
        booksService.addBook(books);

        // Prepare response DTO with added book details
        BooksDTO response = new BooksDTO(
                books.getAuthor(),
                books.getBookName(),
                books.getPublishYear(),
                books.getPublisher(),
                books.getPrice(),
                "A book was added successfully!"
        );

        // Return a success response
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Retrieves a book by its ID.
     * Endpoint: GET /protected/get-book/{id}
     * @param id the ID of the book to retrieve.
     * @return ResponseEntity containing the book details or error message.
     */
    @RequestMapping(value = "/protected/get-book/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getBook(@PathVariable Long id) {
        Optional<Books> books = booksService.getBook(id);

        // Check if the book exists
        if (books.isPresent()) {
            Books book = books.get();

            // Prepare response DTO with retrieved book details
            BooksDTO response = new BooksDTO(
                    book.getAuthor(),
                    book.getBookName(),
                    book.getPublishYear(),
                    book.getPublisher(),
                    book.getPrice(),
                    "A book was retrieved successfully!"
            );
            // Return success response
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            // Prepare error message if book not found
            messageDTO response = new messageDTO(
                    "Book with id "+id+" could not found"
            );
            // Return not found response
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    /**
     * Retrieves all books from the system.
     * Endpoint: GET /protected/get-books/
     * @return ResponseEntity containing the list of books or error message.
     */
    @RequestMapping(value = "/protected/get-books/", method = RequestMethod.GET)
    public ResponseEntity<?> getAllBooks(){

        // Check if there are books available
        if (booksService.getAllBooks() != null && !booksService.getAllBooks().isEmpty()){
            // Return the list of books if found
            return ResponseEntity.status(HttpStatus.OK).body(booksService.getAllBooks());
        } else {
            // Return error message if no books are found
            messageDTO response = new messageDTO(
                    "No uploaded Books was found"
            );
            // Return not found response
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    /**
     * Adds multiple books to the system at once.
     * Endpoint: POST /protected/add-multiple-books/
     * @param books the list of books to add.
     * @return ResponseEntity with the success message.
     */
    @RequestMapping(value = "/protected/add-multiple-books/", method = RequestMethod.POST)
    public ResponseEntity<messageDTO> addMultipleBooks(@RequestBody List<Books> books){

        // Add all the books
        booksService.addMultipleBooks(books);

        // Prepare success message response
        messageDTO response = new messageDTO(
                "All books were added successfully"
        );

        // Return the response with status created
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
