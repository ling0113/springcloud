package com.system.service.impl;

import com.system.dao.UserDao;
import com.system.entity.User;
import com.system.service.UserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName: com.system.service.impl.UserServiceImpl
 * @Description:
 * @Author: lgrong
 * @CreateDate: 2020/3/25 23:24
 * @Version: 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User selectUserByName(String userName) {
        /*User user = new User();
        user.setUserName(userName);
        List<User> list = userDao.select(user);
        return list.get(1);
*/
        return null;
    }

    @Override
    public List<User> selAll() {
        List<User> users = userDao.selectAll();
        return users;
    }
}
