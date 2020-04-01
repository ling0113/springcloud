package com.serviceribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName: com.serviceribbon.HelloServiceImpl
 * @Description:
 * @Author: lgrong
 * @CreateDate: 2020/3/23 21:39
 * @Version: 1.0
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String hiService(String name) {
        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
    }

    @Override
    public String hiTestService(RiTest riTest) {
        return null;
    }
}
