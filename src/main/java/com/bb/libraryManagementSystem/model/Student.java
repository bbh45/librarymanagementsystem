package com.bb.libraryManagementSystem.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String contact;

    private String address;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    @OneToOne
    @JoinColumn
    @JsonIgnoreProperties({"student", "admin", "password"})
    private MyUser myUser;

    @OneToMany(mappedBy = "student")
    @JsonIgnoreProperties({"student","transactionList","creationDate","updatedDate"})
    private List<Book> bookList;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<Transaction> transactionList;

    @CreationTimestamp
    private Date creationDate;

    @UpdateTimestamp
    private Date updatedDate;

}
