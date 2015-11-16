package com.gaussic.controller;

import com.gaussic.EJBtools.EjbTools;
import com.gaussic.HelloWorld;
import com.gaussic.HelloWorldBean;
import com.gaussic.ejb.User;
import com.gaussic.ejb.UserBean;
import com.gaussic.ejb.UserBeanRemote;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ejb.EJB;
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
            /*
            UserBeanRemote ubr = (UserBeanRemote)context.lookup("java:app/EJB-1.0-SNAPSHOT/UserBean!com.gaussic.ejb.UserBeanRemote");
            User user = new User();
            user.setId(4);
            user.setName("bruce lee");
            user.setPwd("123456");
            ubr.createUser(user);
            */
            HelloWorld hw = (HelloWorld)context.lookup("java:app/EJB-1.0-SNAPSHOT/HelloWorldBean!com.gaussic.HelloWorld");
            System.out.println(hw.SayHello("test"));
            return "index";
        }
        catch (Exception e) {
            System.out.println(e.toString());
            return "";
        }
    }

    public static Context initContext() throws javax.naming.NamingException {
        /*
        Properties prop = new Properties();
        prop.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
        prop.put(Context.URL_PKG_PREFIXES,"org.jboss.naming:org.jnp.interfaces");
        prop.put(Context.PROVIDER_URL, "jnp://localhost:1099");

        return new javax.naming.InitialContext(prop);
        */
        Properties properties = new Properties();
        String PKG_INTERFACES = "org.jboss.ejb.client.naming";
        properties.put(Context.URL_PKG_PREFIXES, PKG_INTERFACES);

        Context initialContext = new InitialContext(properties);
        return initialContext;
    }
}