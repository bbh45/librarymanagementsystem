package com.bb.libraryManagementSystem.repository;

import com.bb.libraryManagementSystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
