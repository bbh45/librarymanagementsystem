package com.bb.libraryManagementSystem.controller;

import com.bb.libraryManagementSystem.model.Student;
import com.bb.libraryManagementSystem.request.StudentCreateRequest;
import com.bb.libraryManagementSystem.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public void createStudent(@Valid @RequestBody StudentCreateRequest studentCreateRequest){
        studentService.createStudent(studentCreateRequest);
    }

    @GetMapping("/student/search")
    public Student findStudentById(@RequestParam("id") Integer id){
        return studentService.findStudentById(id);
    }
}
