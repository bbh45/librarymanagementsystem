package com.bb.libraryManagementSystem.service;

import com.bb.libraryManagementSystem.model.Student;
import com.bb.libraryManagementSystem.repository.StudentRepository;
import com.bb.libraryManagementSystem.request.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void createStudent(StudentCreateRequest studentCreateRequest) {
        Student student = studentCreateRequest.to();
        studentRepository.save(student);
    }

    public Student findStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }
}
