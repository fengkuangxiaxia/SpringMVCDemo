package com.gaussic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Cole Fu on 2015/11/11.
 */
@Controller
@RequestMapping(value = "/account")
public class AccountController {

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(){
        return "account/login";
    }

}
