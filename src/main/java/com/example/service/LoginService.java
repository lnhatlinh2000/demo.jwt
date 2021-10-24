package com.example.service;

import com.example.dao.UserDAO;
import com.example.dto.UserDTO;
import com.example.entities.User;

/**
 * LoginService handle authentication and authorization
 */
public class LoginService {
    private static LoginService instance;

    public static LoginService getInstance() {
        if (instance == null) {
            instance = new LoginService();
        }
        return instance;
    }

    public boolean isValidUserDto(UserDTO userDTO) {
        return !(userDTO == null
                || userDTO.getUsername() == null
                || userDTO.getUsername().trim().equals("")
                || userDTO.getPassword() == null
                || userDTO.getPassword().trim().equals(""));
    }

    /**
     * Authenticate user using username and password
     *
     * @param userDTO
     * @return
     */
    public boolean authenticate(UserDTO userDTO) {
        try {
            User user = UserDAO.getInstance().readByUsername(userDTO.getUsername());
            if (user != null && user.getPassword().equals(userDTO.getPassword())) {
                return true;
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return false;
    }
}
