package com.system.aspect;

import com.system.annotation.LogAop;
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
@Aspect //代表切面
@Component
public class LogAsPect {
//https://www.jianshu.com/p/c7d8e83ebf86
    //joinpoint  连接点 可以被选择的 增强的方法点
    //advisor    增强 (相当于通知)
    //pointcut   切点  所有连接点的集合
    //introduction 引入:添加方法或字段到被通知的类
    //weaving    织入 将增强加入到目标类的过程
    //           aop三种织入切面的方法:  第一种:编译时期织入,Aspectj
    //                                 第二种:类装载时期织入 特殊的类装载器
    //                                 第三种:动态代理织入 在运行期为目标类添加增强生成子类的方式



    @Autowired
    private SysLogDao sysLogDao;
    /**
     * 标识匹配带自定义注解的方法
     */
    @Pointcut("@annotation(com.system.annotation.LogAop)")
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
        String value = method.getAnnotation(LogAop.class).value();
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
