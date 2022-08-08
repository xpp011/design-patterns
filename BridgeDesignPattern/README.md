# 桥接模式（Bridge Pattern）



## 定义

Decouple an abstraction from its implementation so that the two can vary independently

将抽象和实现解耦，让它们可以独立变化。



## 经典实现

**JDBC驱动**

```java

Class.forName("com.mysql.jdbc.Driver");//加载及注册JDBC驱动程序
String url = "jdbc:mysql://localhost:3306/sample_db?user=root&password=your_password";
Connection con = DriverManager.getConnection(url);
Statement stmt = con.createStatement()；
String query = "select * from test";
ResultSet rs=stmt.executeQuery(query);
while(rs.next()) {
  rs.getString(1);
  rs.getInt(2);
}
```

在上方的代码中，什么是抽象什么是实现呢

可以看到如果后续要修改其他数据库，只需要修改Driver即可

> Class.forName("oracle.jdbc.driver.OracleDriver");

那么在JDBC中具体的某一个driver是实现，而对于JDBC来说是抽象，就像是抽出一幅骨架一样，负责具体的调用逻辑，不负责具体的实现逻辑

并且JDBC和driver可以独立开发，然后通过组合的方式组装在一起。

![image-20220808225826052](https://typora.xpp011.cn/typora/img/image-20220808225826052.png)





## 实际应用

以下代码是实现了一个告警的例子，不同的告警规则触发不同的告警类型。告警支持多种通知渠道，包括：邮件、短信、微信、自动语音电话。通知的紧急程度有多种类型，包括：SEVERE（严重）、URGENCY（紧急）、NORMAL（普通）、TRIVIAL（无关紧要）。不同的紧急程度对应不同的通知渠道。比如，SERVE（严重）级别的消息会通过“自动语音电话”告知相关人员。

```java

public interface MsgSender {
  void send(String message);
}

public class TelephoneMsgSender implements MsgSender {
  private List<String> telephones;

  public TelephoneMsgSender(List<String> telephones) {
    this.telephones = telephones;
  }

  @Override
  public void send(String message) {
    //...
  }

}

public class EmailMsgSender implements MsgSender {
  // 与TelephoneMsgSender代码结构类似，所以省略...
}

public class WechatMsgSender implements MsgSender {
  // 与TelephoneMsgSender代码结构类似，所以省略...
}

public abstract class Notification {
  protected MsgSender msgSender;

  public Notification(MsgSender msgSender) {
    this.msgSender = msgSender;
  }

  public abstract void notify(String message);
}

public class SevereNotification extends Notification {
  public SevereNotification(MsgSender msgSender) {
    super(msgSender);
  }

  @Override
  public void notify(String message) {
    msgSender.send(message);
  }
}

public class UrgencyNotification extends Notification {
  // 与SevereNotification代码结构类似，所以省略...
}
public class NormalNotification extends Notification {
  // 与SevereNotification代码结构类似，所以省略...
}
public class TrivialNotification extends Notification {
  // 与SevereNotification代码结构类似，所以省略...
}
```

如果说JDBC的例子能够体现抽象和实现的区别，那这个告警的例子还能体现**不同的独立变化的维度(告警类型、告警方式)**，然后通过组合的方式，让这两个维度可以独立进行扩展，互不影响。



**再理解**

> 桥接模式就像是一种骨架，将逻辑抽象出来，负责调度，而不负责具体的实现，同时这副骨架可以通过组合的方式将不同维度的具体实现组合起来(手，脚，头)。桥接模式可以说是面向接口变成的集大成者
>
> 桥接模式大大的降低了类与类之间的复杂度，假设有N个A类，M个B类，如果我们使用继承就需要N*M个类表示所有的状态，而使用桥接模式(组合)的方式只需要N+M+1个类即可
>
> slf4j就是桥接模式很好的体现，其中有三个核心概念，logger,appender和encoder。分别指这个日志记录器负责哪个类的日志，日志打印到哪里以及日志打印的格式。三个纬度上可以有不同的实现，使用者可以在每一纬度上定义多个实现，配置文件中将各个纬度的某一个实现组合在一起就ok了。



