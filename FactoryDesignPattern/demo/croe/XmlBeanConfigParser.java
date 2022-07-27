package FactoryDesignPattern.demo.croe;

import java.io.InputStream;
import java.util.Collections;
import java.util.List;

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
        return Collections.emptyList();
    }
}
