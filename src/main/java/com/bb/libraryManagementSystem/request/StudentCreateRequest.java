package com.bb.libraryManagementSystem.request;

import com.bb.libraryManagementSystem.model.AccountStatus;
import com.bb.libraryManagementSystem.model.MyUser;
import com.bb.libraryManagementSystem.model.Student;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentCreateRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String contact;
    private String address;

    @NotBlank
    private String email;

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    public Student to(){
        return Student.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .contact(this.contact)
                .address(this.address)
                .email(this.email)
                .accountStatus(AccountStatus.ACTIVE)
                .build();
    }

    public UserCreateRequest toUser(){
        return UserCreateRequest.builder()
                .userName(this.userName)
                .password(this.password)
                .student(to())
                .build();
    }
}