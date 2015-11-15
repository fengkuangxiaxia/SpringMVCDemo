package com.gaussic.controller;

import com.gaussic.EJBtools.EjbTools;
import com.gaussic.HelloWorldBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ejb.EJB;

/**
 * Created by dzkan on 2015/10/3.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        try {
            HelloWorldBean hwb = new HelloWorldBean();
            System.out.println(hwb.SayHello("test"));
            return "index";
        }
        catch (Exception e) {
            System.out.println("fail");
            return "";
        }
    }
}