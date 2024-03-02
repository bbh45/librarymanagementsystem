package com.bb.libraryManagementSystem.controller;

import com.bb.libraryManagementSystem.request.BookFilterType;
import com.bb.libraryManagementSystem.request.BookCreateRequest;
import com.bb.libraryManagementSystem.response.BookSeachResponse;
import com.bb.libraryManagementSystem.service.BookService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/book")
    public void createBook(@Valid @RequestBody BookCreateRequest createBookRequest){
        bookService.createBook(createBookRequest);
    }

    @GetMapping("/book/search")
    public List<BookSeachResponse> findBooks(@RequestParam("filter") BookFilterType bookFilterType,
                                                  @RequestParam("value") String value){
        return bookService.findBooks(bookFilterType, value)
                .stream()
                .map(book -> book.to())
                .collect(Collectors.toList());

    }

}
