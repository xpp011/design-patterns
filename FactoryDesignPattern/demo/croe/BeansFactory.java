package FactoryDesignPattern.demo.croe;

import FactoryDesignPattern.demo.exception.BeanClassNotMatchException;
import FactoryDesignPattern.demo.exception.BeanCreationFailureException;
import FactoryDesignPattern.demo.exception.NoSuchBeanDefinitionException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @program: design-patterns
 * @description: bean工厂
 * @author: xpp011
 * @create: 2022-07-27 23:35
 **/

public class BeansFactory {

    private ConcurrentMap<String, Object> singletonObjects = new ConcurrentHashMap<>();

    private ConcurrentMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public void addBeanDefinitions(List<BeanDefinition> beanDefinitions) {
        if (beanDefinitions == null) return;
        //记录bean元数据
        for (BeanDefinition beanDefinition : beanDefinitions) {
            if (beanDefinitionMap.containsKey(beanDefinition.getId())) {
                continue;
            }
            beanDefinitionMap.put(beanDefinition.getId(), beanDefinition);
        }
        //创建bean
        for (BeanDefinition beanDefinition : beanDefinitions) {
            if (beanDefinition.isLazyInit() || !beanDefinition.isSingleton()) {
                continue;
            }
            Object bean = createBean(beanDefinition);
            singletonObjects.put(beanDefinition.getId(), bean);
        }
    }

    public Object getBean(String beanId) {
        checkGetBean(beanId);

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanId);
        if (!beanDefinition.isSingleton()) {
            return createBean(beanDefinition);
        }

        Object bean = singletonObjects.get(beanId);
        if (bean == null) {
            bean = createBean(beanDefinition);
            singletonObjects.put(beanId, bean);
        }
        return bean;
    }


    private <T> T createBean(BeanDefinition beanDefinition) {
        try {
            Class<?> beanClass = Class.forName(beanDefinition.getClassName());
            List<BeanDefinition.ConstructorArg> args = beanDefinition.getArgs();
            if (args == null || args.isEmpty()) {
                return (T) beanClass.newInstance();
            }

            int n = args.size();
            Class[] argTypes = new Class[n];
            Object[] argObjects = new Object[n];
            for (int i = 0; i < n; i++) {
                BeanDefinition.ConstructorArg arg = args.get(i);
                if (!arg.isRef()) {
                    argTypes[i] = arg.getType();
                    argObjects[i] = arg.getArg();
                } else {
                    BeanDefinition argBeanDefinition = beanDefinitionMap.get(arg.getArg());
                    argTypes[i] = Class.forName(argBeanDefinition.getClassName());
                    argObjects[i] = getBean((String) arg.getArg());
                }
            }
            Constructor<?> constructor = beanClass.getConstructor(argTypes);
            return (T) constructor.newInstance(argObjects);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException |
                 ClassNotFoundException e) {
            throw new BeanCreationFailureException(e);
        }
    }

    private boolean checkGetBean(String beanId) {
        if (beanId == null || !beanDefinitionMap.containsKey(beanId)) {
            throw new NoSuchBeanDefinitionException("Bean is not defined: " + beanId);
        }

        return true;
    }
}
