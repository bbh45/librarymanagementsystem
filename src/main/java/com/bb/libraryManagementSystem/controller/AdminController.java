package com.bb.libraryManagementSystem.controller;

import com.bb.libraryManagementSystem.exception.InvalidUserException;
import com.bb.libraryManagementSystem.model.Admin;
import com.bb.libraryManagementSystem.model.MyUser;
import com.bb.libraryManagementSystem.request.AdminCreateRequest;
import com.bb.libraryManagementSystem.service.AdminService;
import com.bb.libraryManagementSystem.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    MyUserDetailsService myUserDetailsService;


    @PostMapping("/create")
    public void create(@Valid @RequestBody AdminCreateRequest adminCreateRequest){
        adminService.createAdmin(adminCreateRequest);
    }

    @GetMapping("/profile")
    public Admin getDetails() throws InvalidUserException{
        MyUser myUser = myUserDetailsService.getAuthenticatedUser();
        if(myUser.getAdmin() == null){
            throw new InvalidUserException("User requesting the details is not an Admin");
        }
        Integer adminId = myUser.getAdmin().getId();
        return adminService.getAdminById(adminId);
    }
}
