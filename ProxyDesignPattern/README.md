# 代理模式（Proxy Design Pattern）





## 定义

为其他对象提供一种代理以控制对这个对象的访问。





## 经典实现

Spring里全是





## 静态代理

```java

public interface IUserController {
  UserVo login(String telephone, String password);
  UserVo register(String telephone, String password);
}

public class UserController implements IUserController {
  //...省略其他属性和方法...

  @Override
  public UserVo login(String telephone, String password) {
    //...省略login逻辑...
    //...返回UserVo数据...
  }

  @Override
  public UserVo register(String telephone, String password) {
    //...省略register逻辑...
    //...返回UserVo数据...
  }
}

public class UserControllerProxy implements IUserController {
  private MetricsCollector metricsCollector;
  private UserController userController;

  public UserControllerProxy(UserController userController) {
    this.userController = userController;
    this.metricsCollector = new MetricsCollector();
  }

  @Override
  public UserVo login(String telephone, String password) {
    long startTimestamp = System.currentTimeMillis();

    // 委托
    UserVo userVo = userController.login(telephone, password);

    long endTimeStamp = System.currentTimeMillis();
    long responseTime = endTimeStamp - startTimestamp;
    RequestInfo requestInfo = new RequestInfo("login", responseTime, startTimestamp);
    metricsCollector.recordRequest(requestInfo);

    return userVo;
  }

  @Override
  public UserVo register(String telephone, String password) {
    long startTimestamp = System.currentTimeMillis();

    UserVo userVo = userController.register(telephone, password);

    long endTimeStamp = System.currentTimeMillis();
    long responseTime = endTimeStamp - startTimestamp;
    RequestInfo requestInfo = new RequestInfo("register", responseTime, startTimestamp);
    metricsCollector.recordRequest(requestInfo);

    return userVo;
  }
}

//UserControllerProxy使用举例
//因为原始类和代理类实现相同的接口，是基于接口而非实现编程
//将UserController类对象替换为UserControllerProxy类对象，不需要改动太多代码
IUserController userController = new UserControllerProxy(new UserController());
```





## 动态代理

### JDK代理

```java
interface IClient {
    void login();
}

class Client implements IClient{

    @Override
    public void login() {
        System.out.println("登录啦");
    }
}


class ClientProxy {

    private Object proxyObject;

    public ClientProxy(Object proxyObject) {
        this.proxyObject = proxyObject;
    }

    public Object getProxy() {
        DynamicProxyHandler proxyHandle = new DynamicProxyHandler(proxyObject);
        Object proxyInstance = Proxy.newProxyInstance(proxyObject.getClass().getClassLoader(), proxyObject.getClass().getInterfaces(), proxyHandle);
        return proxyInstance;
    }

    class DynamicProxyHandler implements InvocationHandler {

        private Object proxyObject;

        public DynamicProxyHandler(Object proxyObject) {
            this.proxyObject = proxyObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(method.getName());
            System.out.println("被代理啦啦啦");
            Object res = method.invoke(proxyObject, args);
            return res;
        }
    }
}


//调用
Client client = new Client();
IClient proxy = (IClient) new ClientProxy(client).getProxy();
proxy.login();
```





### CGLIB代理

关于CGLIB代理曾经写过一篇文章深度解析[链接](https://xpp011.cn/2021/11/18/SpringAOP再理解)

该文章大致描述了

- JDK代理和CGLIB代理的区别
- 为什么Spring不推荐JDK代理
- JDK代理缺点
- JDK代理生成的代理类为什么一定要继承Proxy类
- 为什么无法代理`provide/protected/default`修饰的方法