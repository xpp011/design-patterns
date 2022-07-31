package FactoryDesignPattern.demo;

/**
 * @program: design-patterns
 * @description:
 * @author: xpp011
 * @create: 2022-07-27 23:24
 **/

public interface ApplicationContext {

    <T> T getBean(String beanId);

}
