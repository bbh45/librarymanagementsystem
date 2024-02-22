package com.bb.libraryManagementSystem.request;

import com.bb.libraryManagementSystem.model.AccountStatus;
import com.bb.libraryManagementSystem.model.Student;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentCreateRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String contact;
    private String address;

    @NotBlank
    private String email;

    public Student to(){
        return Student.builder()
                .name(this.name)
                .contact(this.contact)
                .address(this.address)
                .email(this.email)
                .accountStatus(AccountStatus.ACTIVE)
                .build();
    }
}