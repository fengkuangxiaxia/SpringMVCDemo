package com.gaussic.repository;

import com.gaussic.model.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Cole Fu on 2015/11/11.
 */
@Repository // 添加注解
public interface BlogRepository extends JpaRepository<BlogEntity, Integer> {

}
