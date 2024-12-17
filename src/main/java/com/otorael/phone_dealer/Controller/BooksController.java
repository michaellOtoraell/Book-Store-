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

@RestController
@RequestMapping("/api/v1/")
public class BooksController {

    @Autowired
    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @RequestMapping(value = "/protected/add-book", method = RequestMethod.POST)
    public ResponseEntity<?> addBook(@RequestBody  Books books){

        booksService.addBook(books);

        BooksDTO response = new BooksDTO(
                books.getAuthor(),
                books.getBookName(),
                books.getPublishYear(),
                books.getPublisher(),
                books.getPrice(),
                "A book was added successfully!"
        );
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @RequestMapping(value = "/protected/get-book/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getBook(@PathVariable Long id) {
        Optional<Books> books = booksService.getBook(id);

        if (books.isPresent()) {
            Books book = books.get();
            BooksDTO response = new BooksDTO(
                    book.getAuthor(),
                    book.getBookName(),
                    book.getPublishYear(),
                    book.getPublisher(),
                    book.getPrice(),
                    "A book was retrieved successfully!"
            );
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            messageDTO response = new messageDTO(
                    "Book with id "+id+" could not found"
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @RequestMapping(value = "/protected/get-books/", method = RequestMethod.GET)
    public ResponseEntity<?> getAllBooks(){

        if (booksService.getAllBooks() != null && !booksService.getAllBooks().isEmpty()){

            return ResponseEntity.status(HttpStatus.OK).body(booksService.getAllBooks());

        } else {

            messageDTO response = new messageDTO(
                    "No uploaded Books was found"
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    @RequestMapping(value = "/protected/add-multiple-books/", method = RequestMethod.POST)
    public ResponseEntity<messageDTO> addMultipleBooks(@RequestBody List<Books> books){

        booksService.addMultipleBooks(books);
        messageDTO response = new messageDTO(
                "All books were added successfully"
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
