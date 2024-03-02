package com.bb.libraryManagementSystem.controller;

import com.bb.libraryManagementSystem.exception.InvalidUserException;
import com.bb.libraryManagementSystem.model.MyUser;
import com.bb.libraryManagementSystem.model.Student;
import com.bb.libraryManagementSystem.request.StudentCreateRequest;
import com.bb.libraryManagementSystem.service.MyUserDetailsService;
import com.bb.libraryManagementSystem.service.StudentService;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @PostMapping("/create")
    public void createStudent(@Valid @RequestBody StudentCreateRequest studentCreateRequest){
        studentService.createStudent(studentCreateRequest);
    }

    @GetMapping("/admin_search")
    public Student findStudentById(@RequestParam("id") Integer id) throws InvalidUserException{
        MyUser myUser = myUserDetailsService.getAuthenticatedUser();
        if(myUser.getAdmin() == null){
            throw new InvalidUserException("User requesting the details is not an Admin");
        }
        return studentService.findStudentById(id);
    }


    @GetMapping("/profile")
    public Student getDetails() throws InvalidUserException{
        MyUser myUser = myUserDetailsService.getAuthenticatedUser();
        if(myUser.getStudent() == null) {
            throw new InvalidUserException("User requesting the details is not a Student");
        }
        Integer studentId = myUser.getStudent().getId();
        return studentService.findStudentById(studentId);
    }
}
