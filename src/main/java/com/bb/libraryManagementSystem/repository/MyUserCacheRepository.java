package com.bb.libraryManagementSystem.repository;

import com.bb.libraryManagementSystem.model.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class MyUserCacheRepository {

    private final String USER_KEY_PREFIX = "usr::";

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public void set(MyUser myUser){
        String key = getKey(myUser.getUsername());
        redisTemplate.opsForValue().set(key, myUser, 24, TimeUnit.HOURS);
    }

    public MyUser get(String userName){
        return (MyUser)redisTemplate.opsForValue().get(getKey(userName));
    }



    public String getKey(String userName){
        return USER_KEY_PREFIX + userName;
    }

}
