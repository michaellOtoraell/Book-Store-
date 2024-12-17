package com.otorael.phone_dealer.Services.BooksService;

import com.otorael.phone_dealer.Model.Books;
import com.otorael.phone_dealer.Repository.BooksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServices implements BooksService{

    private final BooksRepository booksRepository;

    public BookServices(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public Optional<Books> getBook(Long id) {
        return booksRepository.findById(id);
    }

    @Override
    public Books addBook(Books books) {
        return booksRepository.save(books);
    }

    @Override
    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }

    @Override
    public String addMultipleBooks(List<Books> books) {
        booksRepository.saveAll(books);
        return "success";
    }
}
