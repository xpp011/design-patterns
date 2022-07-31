package FactoryDesignPattern;

import java.io.File;

/**
 * @program: design-patterns
 * @description: 工厂方法‘
 * @author: xpp011
 * @create: 2022-07-27 22:15
 **/

public class FactoryMethodMap {

    public static IFactoryMethod createFactory(String fileExtension) {
        switch (fileExtension.toLowerCase()) {
            case "yaml":
                return new YamlFactoryMethod();
            case "properties":
                return new PropertiesFactoryMethod();
            default:
                throw new RuntimeException("Rule config file format is not supported " + fileExtension);
        }
    }

}

class RuleConfigSourceMap extends RuleConfigSource {

    public static void main(String[] args) {
        RuleConfigSourceMap ruleConfigSourceMap = new RuleConfigSourceMap();
        RuleConfig ruleConfig = ruleConfigSourceMap.load(new File("path"));
    }

    @Override
    RuleConfig load(File file) {
        String fileName = file.getName();
        String fileExtension = getFileExtension(fileName);
        String context = getContext(file);
        IFactoryMethod factory = FactoryMethodMap.createFactory(fileExtension);
        RuleConfig ruleConfig= factory.createParser().parser(context);
        return ruleConfig;
    }
}


interface IFactoryMethod {
    IRuleConfigParser createParser();
}

class YamlFactoryMethod implements IFactoryMethod{
    @Override
    public IRuleConfigParser createParser() {
        //啪啪啪一顿组合和初始化操作
        return new YamlRuleConfigParser();
    }
}

class PropertiesFactoryMethod implements IFactoryMethod{
    @Override
    public IRuleConfigParser createParser() {
        //啪啪啪一顿组合和初始化操作
        return new PropertiesRuleConfigParser();
    }
}




