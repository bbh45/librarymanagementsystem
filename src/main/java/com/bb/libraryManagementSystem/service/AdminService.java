package com.bb.libraryManagementSystem.service;

import com.bb.libraryManagementSystem.model.Admin;
import com.bb.libraryManagementSystem.model.MyUser;
import com.bb.libraryManagementSystem.repository.AdminRepository;
import com.bb.libraryManagementSystem.repository.MyUserRepository;
import com.bb.libraryManagementSystem.request.AdminCreateRequest;
import com.bb.libraryManagementSystem.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AdminService {

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    AdminRepository adminRepository;

    @Transactional
    public void createAdmin(AdminCreateRequest adminCreateRequest){
        UserCreateRequest userCreateRequest = adminCreateRequest.toUser();
        MyUser myUser = myUserDetailsService.createUser(userCreateRequest);

        Admin admin = adminCreateRequest.to();
        admin.setMyUser(myUser);
        adminRepository.save(admin);
    }

    public Admin getAdminById(Integer id){
        return adminRepository.findById(id).orElse(null);
    }
}
