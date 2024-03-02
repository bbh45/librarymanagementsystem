package com.bb.libraryManagementSystem.repository;

import com.bb.libraryManagementSystem.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser, Integer> {

    MyUser findByUserName(String userName);
}
