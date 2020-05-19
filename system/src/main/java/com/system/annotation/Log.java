package com.system.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName: com.system.annotation.log
 * @Description: aop实现方法日志
 * @Author: lgrong
 * @CreateDate: 2020/5/18 18:28
 * @Version: 1.0
 */
@Target(ElementType.METHOD)//方法级别
@Retention(RetentionPolicy.RUNTIME)//运行时
public @interface Log {
    String value() default "";
}
