package com.example.controller;

import com.example.consts.ViewConstant;
import com.example.dto.UserDTO;
import com.example.service.LoginService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

/**
 * LoginController manage login/logout activities of user
 */
@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {

    /**
     * Method GET process and provide login page to user
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(ViewConstant.LOGIN_PAGE).forward(request, response);
    }

    /**
     * Handle login submit with POST method, provide login confirmation page to user with input username and password
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDTO dto = new UserDTO(username, password);

        if (!LoginService.getInstance().isValidUserDto(dto)) {
            request.setAttribute("error", "Invalid input");
            request.getRequestDispatcher(ViewConstant.LOGIN_PAGE).forward(request, response);
            return;
        }

        if (LoginService.getInstance().authenticate(dto)) {
            request.setAttribute("user", dto);
            request.getRequestDispatcher(ViewConstant.LOGIN_CONFIRMATION_PAGE).forward(request, response);
        } else {
            request.setAttribute("error", "Invalid username/password");
            request.getRequestDispatcher(ViewConstant.LOGIN_PAGE).forward(request, response);
        }

    }
}
