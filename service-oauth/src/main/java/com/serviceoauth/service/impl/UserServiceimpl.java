package com.serviceoauth.service.impl;

import com.serviceoauth.dao.TuserDao;
import com.serviceoauth.entity.userdb.Tuser;
import com.serviceoauth.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserDetailsService, UserService {

    @Autowired
    TuserDao tuserDao;

    /**
     * 登录验证
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Tuser user1 = new Tuser();
        user1.setUsername(username);
        //查询用户
        Tuser tuser = tuserDao.selectOne(user1);
        if (tuser == null) {
            throw new UsernameNotFoundException("账户不存在!");
        }
        //查询角色
        //List<Role> roles = tuserDao.getUserRolesByUid(tuser.getId());
        //user1.setRoles(roles);
        //return user;

        //查询用户权限
        List<String> permissions = tuserDao.findPermissionsByUserId(tuser.getId());
        System.out.println(permissions);
        String[] perarray = new String[permissions.size()];
        permissions.toArray(
            perarray);
        //创建userDetails
        UserDetails userDetails = User.withUsername(tuser.getUsername()).password(tuser.getPassword()).authorities(perarray).build();
        System.out.println(userDetails);
        return userDetails;
    }


    @Override
    public List<Tuser> selAll() {
        List<Tuser> tusers = tuserDao.selectAll();
        return tusers;
    }
}