package com.example.dao;

import com.example.entities.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    public List<User> readAll() {
        List<User> users = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            User user = new User();
            user.setUsername("username " + i);
            user.setPassword("password " + i);
            users.add(user);
        }
        return users;
    }

}
