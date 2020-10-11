package com.scholat.doma.service.impl;

import com.scholat.doma.dao.UserDao;
import com.scholat.doma.entity.User;
import com.scholat.doma.util.RandomID;
import com.scholat.doma.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    /**
     * 查询用户,flag = 0 代表登陆查询，flag = 1 代表模糊查询
     * @param userName
     * @param flag flag= [ 0,1 ]
     * @return List<User>
     */
    @Override
    public List<User> SelectUser(String userName, Integer flag) {
        //进行模糊查询
        if(flag == 1){
            userName+="%";
        }
       List<User>userList = userDao.SelectUser(userName,flag);
       return userList;
    }

    @Override
    public User SelectUserById(String userId) {
        return userDao.SelectUserById(userId);
    }

    @Override
    public User SelectUserByName(String userName) {
        return userDao.SelectUserByName(userName);
    }

    /**
     * 创建用户
     * @param userName
     * @return 创建成功 返回 1 创建失败返回 - 1
     */
    @Override
    public Integer InsertUser(String userName,String account,String password) {
        RandomID randomID = new RandomID();
        String userId = randomID.GetRandomId("user");
        Integer res = userDao.InsertUser(userName,userId,account,password);
        return res>0?1:-1;

    }

    @Override
    public User SelectUsersByAccount(String account) {
        return userDao.SelectUsersByAccount(account);
    }

    /**
     * 更新用户名
     * @param newUserName
     * @param userId
     * @return 更新成功 返回 1 更新失败 返回 -1
     */
    @Override
    public Integer UpdateUserName(String newUserName, String userId) {
        Integer res = userDao.UpdateUserName(newUserName,userId);
        return res>0?1:-1;
    }


}
