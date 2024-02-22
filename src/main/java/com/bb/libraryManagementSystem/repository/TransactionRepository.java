package com.bb.libraryManagementSystem.repository;

import com.bb.libraryManagementSystem.model.Book;
import com.bb.libraryManagementSystem.model.Student;
import com.bb.libraryManagementSystem.model.Transaction;
import com.bb.libraryManagementSystem.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    // SELECT * FROM Transaction
    // WHERE student_id=?1 AND book_id=?2
    // AND transactionType='ISSUE'
    // ORDER BY id desc;
    Transaction findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(
            Book book, Student student, TransactionType transactionType
    );
}
