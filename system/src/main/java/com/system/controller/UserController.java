package com.system.controller;

import com.system.entity.User;
import com.system.service.UserService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
  public Map<String, Object> login(@RequestParam(value = "userName") String userName,
      @RequestParam(value = "userPassword") String userPassword) {
	    Map<String,Object> result = new HashMap<String, Object>();User user = null;String retCode = "";
      String retMsg = "";
        if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(userPassword)){
        	retCode = "01";  
        	retMsg = "用户名和密码不能为空";  
        }else{  
            user = userService.selectUserByName(userName);  
            if(null == user){  
            	retCode = "01";  
            	retMsg = "用户不存在";  
            }else{  
                if(userPassword.equals(user.getUserPassword())){  
                	retCode = "00";  
                	retMsg = "登录成功";  
                }else{  
                	retCode = "01";  
                	retMsg = "密码有误";  
                }  
            }  
        }  
        result.put("1001", retCode);
        result.put("msg", retMsg);
		return result;
	}


    @RequestMapping("/sout")
    public List<User> home() {
        return userService.selAll();
    }

    @RequestMapping("/")
    public String a() {
        return "123";
    }


}