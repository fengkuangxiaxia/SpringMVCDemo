package com.gaussic.repository;

import com.gaussic.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Cole Fu on 2015/11/11.
 */
@Repository // 添加注解
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update UserEntity us set us.firstname=:qFirstName, us.lastname=:qLastName, us.password=:qPassword where us.id=:qId")
    public void updateUser(@Param("qFirstName") String firstname, @Param("qLastName") String lastname,
                           @Param("qPassword") String password, @Param("qId") Integer id);


    @Query("select us from UserEntity us where us.firstname=:qFirstName and us.lastname=:qLastName and us.password=:qPassword")
    List<UserEntity> getUserByUserNamePassword(@Param("qFirstName") String firstname, @Param("qLastName") String lastname,
                                   @Param("qPassword") String password);
}
