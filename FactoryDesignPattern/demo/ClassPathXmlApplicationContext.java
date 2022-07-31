package FactoryDesignPattern.demo;

import FactoryDesignPattern.demo.croe.BeanConfigParser;
import FactoryDesignPattern.demo.croe.BeanDefinition;
import FactoryDesignPattern.demo.croe.BeansFactory;
import FactoryDesignPattern.demo.croe.XmlBeanConfigParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @program: design-patterns
 * @description: 加载xml配置文件，缓存bean
 * @author: xpp011
 * @create: 2022-07-27 23:33
 **/

public class ClassPathXmlApplicationContext implements ApplicationContext{

    private BeansFactory beansFactory;

    private BeanConfigParser beanConfigParser;

    public ClassPathXmlApplicationContext(String configLocation) {
        this.beansFactory = new BeansFactory();
        this.beanConfigParser = new XmlBeanConfigParser();
        loadBeanDefinition(configLocation);
    }

    private void loadBeanDefinition(String configLocation) {
        try(InputStream in = this.getClass().getResourceAsStream("/" + configLocation);) {
            if (in == null) {
                throw new RuntimeException("Can not find config file " + configLocation);
            }
            List<BeanDefinition> beanDefinitions = beanConfigParser.parser(in);
            beansFactory.addBeanDefinitions(beanDefinitions);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T getBean(String beanId) {
        return (T) beansFactory.getBean(beanId);
    }
}
