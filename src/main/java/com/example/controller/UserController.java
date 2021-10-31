package com.example.controller;

import com.example.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    private UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value = "/showAll")
    public String showAll(Model model) {
        model.addAttribute("users", userDao.readAll());
        return "show-users";
    }

}
