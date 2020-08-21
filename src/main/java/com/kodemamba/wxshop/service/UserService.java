package com.kodemamba.wxshop.service;

import com.kodemamba.wxshop.dao.UserDao;
import com.kodemamba.wxshop.generate.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User createUserIfNotExists(String tel) {
        User user = new User();
        user.setTel(tel);
        userDao.inserUser(user);
        return new User();
    }
}
