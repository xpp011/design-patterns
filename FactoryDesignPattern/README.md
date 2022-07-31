#  工厂模式（Factory Design Pattern）



## 定义

**工厂方法模式**是一种创建型设计模式， 其在父类中提供一个创建对象的方法， 允许子类决定实例化对象的类型。



## 经典实现

### Spring 

- BeanFactory  简单工厂
- FactoryBean  工厂方法



### JDK

`java.util.Calendar`

![image-20220731215554226](https://typora.xpp011.cn/typora/img/image-20220731215554226.png)



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

现在设想一种场景，创建`IRuleConfigParser`的方法非常复杂，需要各种组合其他类，以及初始化一系列操作，并且每一种`IRuleConfigParser`的初始化和组合类都不同， 那么把创建`IRuleConfigParser`的代码在放置在`createParser`方法中真的合适吗？答案是否定的。

这样就不再符合单一职责和开闭原则以及代码的耦合度变的极高了，每次想修改其中一个都需要面对`createParser`方法中的一大坨代码

显然是不合适的，那怎么办呢，其实只要为每一种创建设立单独的工厂类，并再创建一个简单工厂来创建这些工厂类。说上去很绕，直接上代码

```java
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

```

再为这些工厂类创建一个简单工厂

```java
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
```

这每次修改和扩展，代码的改动非常少，基本上符合开闭原则。

由于样例代码中的创建`IRuleConfigParser`的代码非常单薄，只有一个new关键字，体现不太出来简单工厂的优势。



**工厂方法一定好吗？**

并不是，可以看到代码的整体复杂度又上去了（多了一层），并且如果我们想要扩展一种`IRuleConfigParser`，我就需要创建两个类，所以工厂方法适用于一些复杂的创建逻辑。



## 抽象工厂（Abstract Factory）

现在假设又多了一种`SystemConfigParser`系统配置解析器， 并且需要为他创建各式各样的解析器

>针对规则配置的解析器：基于接口IRuleConfigParser
>
>JsonRuleConfigParser
>
>XmlRuleConfigParser
>
>YamlRuleConfigParser
>
>PropertiesRuleConfigParser
>
>针对系统配置的解析器：基于接口ISystemConfigParser
>
>JsonSystemConfigParser
>
>XmlSystemConfigParser
>
>YamlSystemConfigParser
>
>PropertiesSystemConfigParser

那么需要创建的工厂类就太多了，并且如果未来又扩展了一种解析器，可维护性和扩展性就变得极差了，

抽象工厂就是针对这种非常特殊的场景而诞生的。我们可以让一个工厂负责创建多个不同类型的对象（IRuleConfigParser、ISystemConfigParser 等），而不是只创建一种 parser 对象。这样就可以有效地减少工厂类的个数。具体的代码实现如下所示：

```java

public interface IConfigParserFactory {
  IRuleConfigParser createRuleParser();
  ISystemConfigParser createSystemParser();
  //此处可以扩展新的parser类型，比如IBizConfigParser
}

public class JsonConfigParserFactory implements IConfigParserFactory {
  @Override
  public IRuleConfigParser createRuleParser() {
    return new JsonRuleConfigParser();
  }

  @Override
  public ISystemConfigParser createSystemParser() {
    return new JsonSystemConfigParser();
  }
}

public class XmlConfigParserFactory implements IConfigParserFactory {
  @Override
  public IRuleConfigParser createRuleParser() {
    return new XmlRuleConfigParser();
  }

  @Override
  public ISystemConfigParser createSystemParser() {
    return new XmlSystemConfigParser();
  }
}

// 省略YamlConfigParserFactory和PropertiesConfigParserFactory代码
```













