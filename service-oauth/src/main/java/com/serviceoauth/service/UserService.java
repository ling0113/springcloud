package com.serviceoauth.service;

import com.serviceoauth.entity.userdb.Tuser;
import java.util.List;

/**
 * @ClassName: com.securitydemo1.service.UserService
 * @Description:
 * @Author: lgrong
 * @CreateDate: 2020/4/9 1:25
 * @Version: 1.0
 */
public interface UserService {

    List<Tuser> selAll();
}
