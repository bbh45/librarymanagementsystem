package com.bb.libraryManagementSystem.response;

import com.bb.libraryManagementSystem.model.Author;
import com.bb.libraryManagementSystem.model.Genre;
import com.bb.libraryManagementSystem.model.Student;
import com.bb.libraryManagementSystem.model.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    private Student student;
    private List<Transaction> transactionList;
    private Date creationDate;
    private Date updatedDate;
}
