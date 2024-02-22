package com.bb.libraryManagementSystem.repository;

import com.bb.libraryManagementSystem.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Author findByEmail(String email);

}
