package com.springboot.factory;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Log {
    @Pointcut("execution(* com.springboot.factory.*.*(..))")
    // expression
    private void businessService() {
        System.out.println("this is pointcut");
    }

     @Pointcut("execution(* com.springboot.factory.getName(..))")
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

ApplicationContext context = 
            new ClassPathXmlApplicationContext("Beans.xml");
//    ApplicationContext application = new AnnotationConfigApplicationContext(Student.class);
     Student student = (Student) context.getBean("student");
     student.getName();
     student.getAge();     
     student.printThrowException();
