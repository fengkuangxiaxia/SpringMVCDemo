package com.gaussic.ejb;

import javax.ejb.Remote;

/**
 * Created by Cole Fu on 2015/11/16.
 */
@Remote
public interface UserBeanRemote {
    void createUser(User user); //创建User
    User getUser(int id); //查询并获取User
}
