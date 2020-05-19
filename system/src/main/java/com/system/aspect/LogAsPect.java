package com.system.aspect;

import com.system.annotation.Log;
import com.system.dao.SysLogDao;
import com.system.entity.SysLog;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: com.system.aspect.LogAsPect
 * @Description: 日志的切面
 * @Author: lgrong
 * @CreateDate: 2020/5/18 18:35
 * @Version: 1.0
 */
@Slf4j
@Aspect
@Component
public class LogAsPect {
//https://www.jianshu.com/p/c7d8e83ebf86

    @Autowired
    private SysLogDao sysLogDao;
    /**
     * 标识匹配带自定义注解的方法
     */
    @Pointcut("@annotation(com.system.annotation.Log)")
    public void pointcut() {}

    // 第一个*代表返回类型不限
    // 第二个*代表所有类
    // 第三个*代表所有方法
    // (..) 代表参数不限     选择范围的aop
    @Pointcut("execution(public * com.system.controller.*.*(..))")
    public void pointcut2() {}

    /**
     * 环绕通知
     */
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) {
        Object result =null;
        long beginTime = System.currentTimeMillis();
        try {
            log.info("@Around 进入环绕通知了");
            long endTime = System.currentTimeMillis();
            result = point.proceed();
            log.info("@Around 运行完了,还在环绕通知了");
            insertLog(point,endTime-beginTime);
        }catch (Throwable e){

        }
        return result;
    }

    @Before("pointcut()")
    public void Before(JoinPoint point) {
        log.info("@Before 进入前置通知了");
    }

    @After("pointcut()")
    public void After(JoinPoint  point) {
        log.info("@After 进入后置通知了");
    }

    @AfterReturning("pointcut()")
    public void AfterReturning(JoinPoint point) {
        log.info("@AfterReturning 进入运行通知了");
    }

    @AfterThrowing("pointcut()")
    public void AfterThrowing(JoinPoint point) {
        log.info("@AfterThrowing 进入异常通知了");
    }



    /**
     * 切面添加日志的方法
     * @param point
     * @param l
     */
    private void insertLog(ProceedingJoinPoint  point, long l) {
        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) point.getSignature();
        //获取方法
        Method method = signature.getMethod();
        log.info("获取方法, {}",method);
        // 请求的类名
        String className = point.getTarget().getClass().getName();
        log.info("请求的类名, {}",className);
        // 请求的方法名
        String methodName = signature.getName();
        log.info("请求的方法名, {}",methodName);
        // 请求的方法参数值
        String args = Arrays.toString(point.getArgs());
        log.info("请求的方法参数值, {}",args);
        //获取注解描述
        String value = method.getAnnotation(Log.class).value();
        log.info("获取注解描述, {}",value);

        SysLog sysLog = new SysLog();
        sysLog.setId(122);
        sysLog.setElapsedTime((int) l);
        sysLog.setUserId("123");
        sysLog.setUserAction(methodName);
        sysLog.setCreateTime(new Date());

        sysLogDao.insertSelective(sysLog);



    }


}
