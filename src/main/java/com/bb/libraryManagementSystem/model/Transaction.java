package com.bb.libraryManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String externalTxnId;

    @ManyToOne
    @JoinColumn
    private Book book;

    @ManyToOne
    @JoinColumn
    private Student student;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private double payment;

    @CreationTimestamp
    private Date transactionDate;
}
