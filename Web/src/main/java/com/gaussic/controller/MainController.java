package com.gaussic.controller;

import com.gaussic.ejb.stateless.HelloWorld;
import com.gaussic.ejb.entiry.User;
import com.gaussic.ejb.stateless.UserBeanRemote;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

/**
 * Created by dzkan on 2015/10/3.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        try {
            Context context = initContext();

            UserBeanRemote ubr = (UserBeanRemote)context.lookup("java:app/EJB-1.0-SNAPSHOT/UserBean!com.gaussic.ejb.stateless.UserBeanRemote");
            User user = new User();
            user.setName("bruce lee");
            user.setPwd("123456");
            ubr.createUser(user);

            HelloWorld hw = (HelloWorld)context.lookup("java:app/EJB-1.0-SNAPSHOT/HelloWorldBean!com.gaussic.ejb.stateless.HelloWorld");
            System.out.println(hw.SayHello("test"));
            return "index";
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return "404";
        }
    }

    public static Context initContext() throws javax.naming.NamingException {
        Properties properties = new Properties();
        String PKG_INTERFACES = "org.jboss.ejb.client.naming";
        properties.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);

        Context initialContext = new InitialContext(properties);
        return initialContext;
    }
}