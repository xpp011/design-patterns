package FactoryDesignPattern.demo.croe;

import FactoryDesignPattern.demo.entity.A;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import FactoryDesignPattern.demo.croe.BeanDefinition.*;
import FactoryDesignPattern.demo.entity.B;

/**
 * @program: design-patterns
 * @description:
 * @author: xpp011
 * @create: 2022-07-27 23:38
 **/

public class XmlBeanConfigParser implements BeanConfigParser{

    @Override
    public List<BeanDefinition> parser(InputStream in) {
        //啪啪啪一顿解析xml文件
        //return Collections.emptyList();

        List<BeanDefinition> list = new ArrayList<>();

        //bean A
        Supplier<BeanDefinition> a = () -> {
            ConstructorArg arg = new ConstructorArg(false, String.class, "张三");
            BeanDefinition beanDefinition = new BeanDefinition(A.class.getName(), "a", arg);
            return beanDefinition;
        };

        //bean B
        Supplier<BeanDefinition> b = () -> {
            ConstructorArg arg1 = new ConstructorArg(false, String.class, "李四");
            ConstructorArg arg2 = new ConstructorArg(true, A.class, "a");
            BeanDefinition beanDefinition = new BeanDefinition(B.class.getName(), "b", arg1, arg2);
            return beanDefinition;
        };

        list.add(b.get());
        list.add(a.get());
        return list;
    }
}
