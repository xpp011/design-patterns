package FactoryDesignPattern.demo.croe;

import java.io.InputStream;
import java.util.List;

/**
 * @program: design-patterns
 * @description: bena配置解析类
 * @author: xpp011
 * @create: 2022-07-27 23:36
 **/

public interface BeanConfigParser {

    List<BeanDefinition> parser(InputStream in);

}
