package com.bb.libraryManagementSystem.model;

import com.bb.libraryManagementSystem.response.BookSeachResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;
    private int cost;

    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @ManyToOne
    @JoinColumn
    private Author author;

    @ManyToOne
    @JoinColumn
    private Student student;

    @OneToMany(mappedBy = "book")
    private List<Transaction> transactionList;

    @CreationTimestamp
    private Date creationDate;

    @UpdateTimestamp
    private Date updatedDate;

    public BookSeachResponse to(){
        return BookSeachResponse.builder()
                .id(this.id)
                .name(this.name)
                .cost(this.cost)
                .genre(this.genre)
                .author(this.author)
                .student(this.student)
                .transactionList(this.transactionList)
                .creationDate(this.creationDate)
                .updatedDate(this.updatedDate)
                .build();
    }
}