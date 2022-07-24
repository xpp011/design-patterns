package FactoryDesignPattern;

import com.sun.deploy.util.StringUtils;
import jdk.management.resource.ResourceContext;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: design-patterns
 * @description: 简单工厂
 * @author: xpp011
 * @create: 2022-07-24 23:06
 **/

public class SimpleFactory {

    static Map<String, IRuleConfigParser> map = new HashMap<>();

    static {
        map.put("yaml", new YamlRuleConfigParser());
        map.put("properties", new PropertiesRuleConfigParser());
    }

    public static IRuleConfigParser createParser(String fileExtension) {
        switch (fileExtension.toLowerCase()) {
            case "`yaml`":
                return new YamlRuleConfigParser();
            case "properties":
                return new PropertiesRuleConfigParser();
            default:
                throw new RuntimeException("Rule config file format is not supported " + fileExtension);
        }
    }

    public static IRuleConfigParser createParserMap(String fileExtension) {
        if (!map.containsKey(fileExtension.toLowerCase())) {
            throw new RuntimeException("Rule config file format is not supported " + fileExtension);
        }
        return map.get(fileExtension.toLowerCase());
    }

}


class RuleConfigSource {
    RuleConfig load(File file) {
        String fileName = file.getName();
        String fileExtension = getFileExtension(fileName);
        String context = getContext(file);
        IRuleConfigParser parser = SimpleFactory.createParser(fileExtension);

        RuleConfig ruleConfig = parser.parser(context);
        return ruleConfig;
    }

    String getFileExtension(String fileName) {
        if (fileName == null || "".equals(fileName)) {
            return null;
        }
        int ixd = fileName.lastIndexOf(".");
        return fileName.substring(ixd);
    }

    String getContext(File file) {
        //获取内容
        String context = "";
        return context;
    }
}

class YamlRuleConfigParser implements IRuleConfigParser {

    @Override
    public RuleConfig parser(String context) {
        //啪啪啪一顿解析
        return new RuleConfig();
    }
}

class PropertiesRuleConfigParser implements IRuleConfigParser {

    @Override
    public RuleConfig parser(String context) {
        //啪啪啪一顿解析
        return new RuleConfig();
    }
}


interface IRuleConfigParser {

    RuleConfig parser(String context);

}

class RuleConfig {

}