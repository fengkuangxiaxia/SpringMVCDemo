package com.gaussic.ejb;

import com.gaussic.HelloWorld;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Cole Fu on 2015/11/16.
 */
@Stateless
//@Stateless
//@Local({UserBeanRemote.class})
//@LocalBean
public class UserBean implements UserBeanRemote {
    @PersistenceContext(unitName = "entity")
    private EntityManager manager;

    public void createUser(User user) {
        manager.persist(user);
    }

    public User getUser(int id) {
        return manager.find(User.class, id);
    }
}