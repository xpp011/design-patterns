package FactoryDesignPattern.demo;

import FactoryDesignPattern.demo.entity.A;
import FactoryDesignPattern.demo.entity.B;

/**
 * @program: design-patterns
 * @description:
 * @author: xpp011
 * @create: 2022-07-27 23:23
 **/

public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("FactoryDesignPattern/demo/resource/beans.xml");
        A a = (A) context.getBean("a");
        B b = (B) context.getBean("b");

        System.out.println(a);
        System.out.println(b);
    }

}
