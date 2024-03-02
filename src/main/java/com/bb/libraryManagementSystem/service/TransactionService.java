package com.bb.libraryManagementSystem.service;

import com.bb.libraryManagementSystem.exception.TransactionServiceException;
import com.bb.libraryManagementSystem.model.Book;
import com.bb.libraryManagementSystem.model.Student;
import com.bb.libraryManagementSystem.model.Transaction;
import com.bb.libraryManagementSystem.model.TransactionType;
import com.bb.libraryManagementSystem.repository.TransactionRepository;
import com.bb.libraryManagementSystem.request.BookFilterType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    StudentService studentService;

    @Autowired
    BookService bookService;

    @Autowired
    TransactionRepository transactionRepository;

    @Value("${book.return.due_date}")
    int daysToReturn;

    Logger logger = LoggerFactory.getLogger(TransactionService.class);
    public String createIssueTransaction(Integer studentId, Integer bookId) throws TransactionServiceException{
        Student student = studentService.findStudentById(studentId);
//        if(student == null){
//            throw new TransactionServiceException("Student is not present in Library");
//        }

        List<Book> books = bookService.findBooks(BookFilterType.BOOK_ID, String.valueOf(bookId));
        if(books == null || books.size() != 1 || books.get(0).getStudent() != null){
            throw new TransactionServiceException("Book is not present in Library");
        }

        Transaction transaction = Transaction.builder()
                            .book(books.get(0))
                            .student(student)
                            .transactionType(TransactionType.ISSUE)
                            .payment(books.get(0).getCost())
                            .externalTxnId(UUID.randomUUID().toString())
                            .build();

        transactionRepository.save(transaction);

        books.get(0).setStudent(student);
        bookService.updateBook(books.get(0));

        return transaction.getExternalTxnId();
    }

    public String createReturnTransaction(Integer studentId, Integer bookId) throws TransactionServiceException{
        Student student = studentService.findStudentById(studentId);
//        if(student == null){
//            throw new TransactionServiceException("Student is not present in Library");
//        }

        List<Book> books = bookService.findBooks(BookFilterType.BOOK_ID, String.valueOf(bookId));
        if(books == null || books.size() != 1){
            throw new TransactionServiceException("Book is not present in Library");
        }

        if(books.get(0).getStudent() == null || books.get(0).getStudent().getId() != studentId){
            throw new TransactionServiceException("Book is not issued to this student");
        }

        Transaction issueTransaction = transactionRepository.findTopByBookAndStudentAndTransactionTypeOrderByIdDesc(
                books.get(0), student, TransactionType.ISSUE
        );

        logger.info("Issue Transaction date: {}, Transaction Id: {}",issueTransaction.getTransactionDate(), issueTransaction.getId());

        Transaction transaction = Transaction.builder()
                .book(books.get(0))
                .student(student)
                .transactionType(TransactionType.RETURN)
                .payment(calculateFine(issueTransaction))
                .externalTxnId(UUID.randomUUID().toString())
                .build();

        transactionRepository.save(transaction);

        books.get(0).setStudent(null);
        bookService.updateBook(books.get(0));

        return transaction.getExternalTxnId();
    }

    private double calculateFine(Transaction issueTransaction){
        long issueTime = issueTransaction.getTransactionDate().getTime();
        long returnTime = System.currentTimeMillis();

        long diff = returnTime - issueTime;
        long daysPassed = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        logger.info("Days passed after issue: {}",daysPassed);

        if(daysPassed >= daysToReturn){
            return (daysPassed - daysToReturn)* 1.0;
        }
        return 0.0;

    }

}
