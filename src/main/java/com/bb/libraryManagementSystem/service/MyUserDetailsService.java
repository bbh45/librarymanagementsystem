package com.bb.libraryManagementSystem.service;

import com.bb.libraryManagementSystem.model.MyUser;
import com.bb.libraryManagementSystem.repository.MyUserCacheRepository;
import com.bb.libraryManagementSystem.repository.MyUserRepository;
import com.bb.libraryManagementSystem.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Value("${users.student.authority}")
    private String studentAuthority;

    @Value("${users.admin.authority}")
    private String adminAuthority;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MyUserRepository userRepository;

    @Autowired
    MyUserCacheRepository myUserCacheRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = myUserCacheRepository.get(username);
        if(myUser == null){
            myUser = userRepository.findByUserName(username);
            if(myUser != null){
                myUserCacheRepository.set(myUser);
            }
        }
        return myUser;
    }

    public MyUser createUser(UserCreateRequest userCreateRequest) {

        MyUser.MyUserBuilder userBuilder = MyUser.builder()
                .userName(userCreateRequest.getUserName())
                .password(passwordEncoder.encode(userCreateRequest.getPassword()));

        if(userCreateRequest.getStudent() != null){
            userBuilder.authorities(studentAuthority);
        }else{
            userBuilder.authorities(adminAuthority);
        }
        return userRepository.save(userBuilder.build());
    }

    public MyUser getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (MyUser)authentication.getPrincipal();
    }
}
