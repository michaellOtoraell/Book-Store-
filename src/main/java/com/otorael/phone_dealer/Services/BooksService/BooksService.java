package com.otorael.phone_dealer.Services.BooksService;

import com.otorael.phone_dealer.Model.Books;

import java.util.List;
import java.util.Optional;

public interface BooksService {
    Optional<Books> getBook(Long id);
    Books addBook(Books books);
    List<Books> getAllBooks();
    String addMultipleBooks(List<Books> books);

}
