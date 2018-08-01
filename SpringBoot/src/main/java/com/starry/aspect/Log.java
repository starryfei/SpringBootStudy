package com.starry.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 定义切面
 */
@Aspect
@Component
public class Log {
//    @Pointcut("execution(*    com.starry.aspect.*.*(..))")
    @Pointcut("@annotation(com.starry.aspect.LogAspect)")
    private void businessService() {
        System.out.println("this is pointcut");
    }

     @Pointcut("execution(* com.starry.aspect.Student.getName(..))")
     private void getName() {
     System.out.println("this is getName");
     }
     
    @Before("businessService()")
    public void doBeforeTask() {
        System.out.println("this is before");
    }

    @After("businessService()")
    public void doAfterTask() {
        System.out.println("this is after");
    }

    @AfterReturning(pointcut = "businessService()", returning = "retVal")
    public void doAfterReturnningTask(Object retVal) {
        // you can intercept retVal here.
        System.out.println("this is " + retVal);
    }

    @AfterThrowing(pointcut = "businessService()", throwing = "ex")
    public void doAfterThrowingTask(Exception ex) {
        // you can intercept thrown exception here.
        System.out.println("this is " + ex.getMessage());
    }
}


