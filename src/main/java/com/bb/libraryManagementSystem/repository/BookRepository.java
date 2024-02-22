package com.bb.libraryManagementSystem.repository;

import com.bb.libraryManagementSystem.model.Author;
import com.bb.libraryManagementSystem.model.Book;
import com.bb.libraryManagementSystem.model.Genre;
import com.bb.libraryManagementSystem.response.BookSeachResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    //select * from Book where name = ?1;
    List<Book> findByName(String Name);

    //select * from Book book, Author author
    // where author.name = ?1 and book.author_id = author.id;
    List<Book> findByAuthor_name(String authorName);

    //select * from Book where genre = ?1;
    List<Book> findByGenre(Genre genre);

    //select * from Book where cost = ?1;
    List<Book> findByCost(Integer cost);
}
