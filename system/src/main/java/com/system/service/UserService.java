package com.system.service;

import com.system.entity.User;
import java.util.List;

/**
 * @ClassName: com.system.service.UserService
 * @Description:
 * @Author: lgrong
 * @CreateDate: 2020/3/25 23:24
 * @Version: 1.0
 */
public interface UserService {

    User selectUserByName(String userName);

    List<User> selAll();


}
