package com.gaussic.controller;

import com.gaussic.model.UserEntity;
import com.gaussic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Cole Fu on 2015/11/11.
 */
@Controller
@RequestMapping(value = "/account")
public class AccountController {
    // 自动装配
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(){
        return "account/login";
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String loginPost(@RequestParam(value="firstname") String firstname, @RequestParam(value="lastname") String lastname, @RequestParam(value="password") String password, HttpSession session, HttpServletResponse resp) {
        List<UserEntity> users = userRepository.getUserByUserNamePassword(firstname, lastname, password);
        if(users == null) {
            return "account/login";
        }

        else {
            session.setAttribute("loginUser", users.get(0));
            return "index";
        }
    }

}
