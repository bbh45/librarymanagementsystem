package com.bb.libraryManagementSystem.request;

import com.bb.libraryManagementSystem.model.Admin;
import com.bb.libraryManagementSystem.model.MyUser;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminCreateRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String contact;

    @NotBlank
    private String email;

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    public Admin to() {
        return Admin.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .contact(this.contact)
                .email(this.email)
                .build();
    }

    public UserCreateRequest toUser() {
        return UserCreateRequest.builder()
                .userName(this.userName)
                .password(this.password)
                .admin(to())
                .build();
    }


}
