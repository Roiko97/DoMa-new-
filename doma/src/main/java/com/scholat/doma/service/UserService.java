package com.scholat.doma.service;

import com.scholat.doma.entity.User;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<User> SelectUser(String userName, Integer flag);

    public User SelectUserById(String userId);
    public User SelectUserByName(String userName);

    public Integer InsertUser(String userName,String account,String password);
    public Integer UpdateUserName(String newUserName,String userId);


    public User SelectUsersByAccount(String account);


}
