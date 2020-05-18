package com.system.dao;


import com.system.entity.User;
import java.util.List;
import tk.mybatis.mapper.common.Mapper;

public interface UserDao extends Mapper<User> {

    List<User> selAll();


}