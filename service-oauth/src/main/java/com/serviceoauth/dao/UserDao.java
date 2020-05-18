package com.serviceoauth.dao;


import com.serviceoauth.entity.Role;
import com.serviceoauth.entity.User;
import java.util.List;
import tk.mybatis.mapper.common.Mapper;

public interface UserDao extends Mapper<User> {

    List<Role> getUserRolesByUid(Integer id);

    List<User> selAll();
}