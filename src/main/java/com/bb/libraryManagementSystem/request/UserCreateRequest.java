package com.bb.libraryManagementSystem.request;

import com.bb.libraryManagementSystem.model.Admin;
import com.bb.libraryManagementSystem.model.Student;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateRequest {

    private String userName;
    private String password;
    private String autorities;
    private Student student;
    private Admin admin;
}
