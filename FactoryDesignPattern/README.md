#  工厂模式（Factory Design Pattern）



## 定义

**工厂方法模式**是一种创建型设计模式， 其在父类中提供一个创建对象的方法， 允许子类决定实例化对象的类型。



## 经典实现





## 简单工厂（Simple Factory）

以一个配置文件解析器为例，建造一个简单工厂模式

可以看到，关于创建对象的的`if-else`逻辑被移至到了Factory工厂类中，和业务代码进行了解耦

```java
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
```



如果后续我们想扩展解析类，只需创建一个继承了`IRuleConfigParser`接口的解析类，并且在Factory工厂类中修改`switch`代码逻辑

即可，虽然扩展一个类需要修改原先的`switch`代码逻辑稍微违反了开闭原则（对修改关闭，对扩展开放），但只要不是频繁修改还是可以接受的。



```java
public class SimpleFactory {

    public static IRuleConfigParser createParser(String fileExtension) {
        switch (fileExtension.toLowerCase()) {
            case "yaml":
                return new YamlRuleConfigParser();
            case "properties":
                return new PropertiesRuleConfigParser();
            default:
                throw new RuntimeException("Rule config file format is not supported " + fileExtension);
        }
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
```





如果想要完全消除`switch`逻辑判断可以使用map

```java
		static Map<String, IRuleConfigParser> map = new HashMap<>();
    
    static {
        map.put("yaml", new YamlRuleConfigParser());
        map.put("properties", new PropertiesRuleConfigParser());
    }
    
    public static IRuleConfigParser createParserMap(String fileExtension) {
        if (!map.containsKey(fileExtension.toLowerCase())) {
            throw new RuntimeException("Rule config file format is not supported " + fileExtension);
        }
        return map.get(fileExtension.toLowerCase());
    }

```



如果使用Spring框架的话，可以做到扩展一个解析类时完全不修改Factory工厂类中的代码，即在创建工厂Bean时通过Spring上下文获取所有继承至`IRuleConfigParser`的bean，放置到map中，这里就不给出代码了很简单。





## 工厂方法（Factory Method）



使用Supplier优化