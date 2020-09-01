package com.system.redis;

import com.system.entity.User;
import com.system.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @ClassName: com.system.redis.RedisTest
 * @Description: redis 测试
 * @Author: lgrong
 * @CreateDate: 2020/5/22 11:28
 * @Version: 1.0
 */
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 添加字符串
     */
    @Test
    public void setString(){
        redisTemplate.opsForValue().set("userKey","ling");
        redisTemplate.opsForValue().set("agekey",24);
        redisTemplate.opsForValue().set("cityKey","美国");
        System.out.println("执行了");
    }

    @Test
    public void hmSet(){
        RedisUtil redisUtil = new RedisUtil();
        //哈希 添加  可以是对象
        //redisUtil.hmSet("LineHashSet","hashcode1","测试hashset值",1000000, TimeUnit.DAYS);
        User user = new User();
        user.setId(1);
        user.setUserName("4444");
        user.setUserPhone("111");
        user.setUserPassword("4444");

        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put(user.getId().toString(),user.getUserName(),user.getUserPassword());
        //redisTemplate.expire("LineHashSet2", 40, TimeUnit.DAYS);
    }
}
