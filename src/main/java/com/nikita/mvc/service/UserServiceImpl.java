package com.nikita.mvc.service;

import com.nikita.mvc.dao.UserDao;
import com.nikita.mvc.model.User;
import com.nikita.mvc.service.UserService;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public void addUser(User user) {
        this.userDao.addUser(user);
    }

    @Transactional
    public void updateUser(User user) {
        this.userDao.updateUser(user);
    }

    @Transactional
    public void removeUser(int id) {
        this.userDao.removeUser(id);
    }

    @Transactional
    public User getUser(int id) {
        return this.userDao.getUser(id);
    }

    @Transactional
    public List<User> getUsers(Long page) {
        return this.userDao.getUsers(page);
    }

    @Transactional
    public List<User> getUsers(String name) {
        return this.userDao.getUsers(name);
    }
}