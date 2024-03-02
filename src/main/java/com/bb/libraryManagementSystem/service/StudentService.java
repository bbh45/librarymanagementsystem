package com.bb.libraryManagementSystem.service;

import com.bb.libraryManagementSystem.model.MyUser;
import com.bb.libraryManagementSystem.model.Student;
import com.bb.libraryManagementSystem.repository.StudentRepository;
import com.bb.libraryManagementSystem.request.StudentCreateRequest;
import com.bb.libraryManagementSystem.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Transactional
    public void createStudent(StudentCreateRequest studentCreateRequest) {

        //creating UserCreateRequest object from StudentCreateRequest and
        //passing it to myUserDetailsService to create MyUser Object
        UserCreateRequest userCreateRequest = studentCreateRequest.toUser();
        MyUser myUser = myUserDetailsService.createUser(userCreateRequest);

        Student student = studentCreateRequest.to();
        student.setMyUser(myUser);
        studentRepository.save(student);
    }

    public Student findStudentById(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }
}
