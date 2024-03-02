package com.bb.libraryManagementSystem.controller;

import com.bb.libraryManagementSystem.exception.InvalidUserException;
import com.bb.libraryManagementSystem.exception.TransactionServiceException;
import com.bb.libraryManagementSystem.model.MyUser;
import com.bb.libraryManagementSystem.model.Student;
import com.bb.libraryManagementSystem.service.MyUserDetailsService;
import com.bb.libraryManagementSystem.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @GetMapping("/transaction/issue")
    public String createIssueTransaction(@RequestParam("book") Integer bookId) throws TransactionServiceException, InvalidUserException{
        Student student = myUserDetailsService.getAuthenticatedUser().getStudent();
        if(student == null){
            throw  new InvalidUserException("User requesting the issue is not a Student");
        }
        Integer studentId = student.getId();
        return transactionService.createIssueTransaction(studentId, bookId);
    }

    @GetMapping("/transaction/return")
    public String createReturnTransaction(@RequestParam("book") Integer bookId) throws TransactionServiceException, InvalidUserException{
        Student student = myUserDetailsService.getAuthenticatedUser().getStudent();
        if(student == null){
            throw  new InvalidUserException("User requesting the issue is not a Student");
        }
        Integer studentId = student.getId();
        return transactionService.createReturnTransaction(studentId, bookId);
    }
}
