package FactoryDesignPattern.demo.croe;

import java.util.List;

/**
 * @program: design-patterns
 * @description: bean工厂
 * @author: xpp011
 * @create: 2022-07-27 23:35
 **/

public class BeansFactory {

    private List<BeanDefinition> beanDefinitions;

    public void addBeanDefinitions(List<BeanDefinition> _beanDefinitions) {
        this.beanDefinitions = _beanDefinitions;
    }

    public <T> T getBean(String beanId, Class<T> clzss) {

    }

    private <T> T createBean() {

    }
}
