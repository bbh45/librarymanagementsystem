package com.bb.libraryManagementSystem.response;

import com.bb.libraryManagementSystem.model.Author;
import com.bb.libraryManagementSystem.model.Genre;
import com.bb.libraryManagementSystem.model.Student;
import com.bb.libraryManagementSystem.model.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookSeachResponse {
    private Integer id;
    private String name;
    private int cost;
    private Genre genre;
    @JsonIgnoreProperties({"bookList","addedOn"})
    private Author author;
    @JsonIgnoreProperties({"accountStatus","myUser","bookList","transactionList","creationDate","updatedDate"})
    private Student student;
    @JsonIgnore
    private List<Transaction> transactionList;
    private Date creationDate;
    private Date updatedDate;
}
