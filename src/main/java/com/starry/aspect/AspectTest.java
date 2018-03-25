package com.starry.aspect;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * study Aspect
 */
public class AspectTest{

    public static void main(String[] args){
        ApplicationContext context =
                new ClassPathXmlApplicationContext("Beans.xml");
//    ApplicationContext application = new AnnotationConfigApplicationContext(Student.class);
        Student student = (Student) context.getBean("student");
        student.getName();
        student.getAge();
        student.printThrowException();
    }
}
