package com.gaussic;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Created by Cole Fu on 2015/11/12.
 */
@Stateless
@Local({HelloWorld.class})
@LocalBean
public class HelloWorldBean implements HelloWorld {
    public String SayHello(String name) {
        return name +"说：Hello World.";
    }

}
