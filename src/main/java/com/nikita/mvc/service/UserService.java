package com.nikita.mvc.service;

import com.nikita.mvc.model.User;
import java.util.List;

public interface UserService {
    void addUser(User user);

    void updateUser(User user);

    void removeUser(int id);

    User getUser(int id);

    List<User> getUsers(Long page);

    List<User> getUsers(String name);
}