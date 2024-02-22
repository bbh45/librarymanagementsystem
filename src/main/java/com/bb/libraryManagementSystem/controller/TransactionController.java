package com.bb.libraryManagementSystem.controller;

import com.bb.libraryManagementSystem.Exception.TransactionServiceException;
import com.bb.libraryManagementSystem.model.Transaction;
import com.bb.libraryManagementSystem.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/transaction/issue")
    public String createIssueTransaction(@RequestParam("student")Integer studentId,
                                         @RequestParam("book") Integer bookId) throws TransactionServiceException{
        return transactionService.createIssueTransaction(studentId, bookId);
    }

    @GetMapping("/transaction/return")
    public String createReturnTransaction(@RequestParam("student")Integer studentId,
                                          @RequestParam("book") Integer bookId) throws TransactionServiceException{
        return transactionService.createReturnTransaction(studentId, bookId);
    }
}
