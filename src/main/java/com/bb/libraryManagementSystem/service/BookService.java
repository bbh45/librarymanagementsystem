package com.bb.libraryManagementSystem.service;

import com.bb.libraryManagementSystem.model.Author;
import com.bb.libraryManagementSystem.model.Book;
import com.bb.libraryManagementSystem.model.Genre;
import com.bb.libraryManagementSystem.repository.AuthorRepository;
import com.bb.libraryManagementSystem.repository.BookRepository;
import com.bb.libraryManagementSystem.request.BookFilterType;
import com.bb.libraryManagementSystem.request.BookCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public void createBook(BookCreateRequest createBookRequest) {

        Book book = createBookRequest.to();

        Author author = book.getAuthor();
        Author authorFromDB = authorRepository.findByEmail(author.getEmail());
        if(authorFromDB == null){
            authorFromDB = authorRepository.save(author);
        }
        book.setAuthor(authorFromDB);
        bookRepository.save(book);
    }

    public List<Book> findBooks(BookFilterType bookFilterType, String value) {
        switch (bookFilterType){
            case NAME :
                return bookRepository.findByName(value);
            case AUTHOR_NAME:
                return bookRepository.findByAuthor_name(value);
            case GENRE:
                return bookRepository.findByGenre(Genre.valueOf(value));
            case COST:
                return bookRepository.findByCost(Integer.valueOf(value));
            case BOOK_ID:
                return bookRepository.findAllById(Collections.singletonList(Integer.valueOf(value)));
        }
        return new ArrayList<>();
    }

    public void updateBook(Book book){
        bookRepository.save(book);
    }
}

