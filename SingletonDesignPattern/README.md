# 单例模式(Singleton Design Pattern)



## 定义

**单例模式**是一种创建型设计模式， 让你能够保证一个类只有一个实例， 并提供一个访问该实例的全局节点。

如果你的代码能够访问单例类， 那它就能调用单例类的静态方法。 无论何时调用该方法， 它总是会返回相同的对象。

单例模式有很多好处，它能够避免实例对象的重复创建，不仅可以减少每次创建对象的时间开销，还可以节约内存空间（比如spring管理的无状态bean）；还**能够避免**由于操作多个实例导致的逻辑错误。**如果一个对象有可能贯穿整个应用程序，而且起到了全局统一管理控制的作用，那么单例模式也许是一个值得考虑的选择。**





**哪些场景适合做单例？**

从业务概念上，有些数据在系统中只应该保存一份，就比较适合设计为单例类。比如，系统的配置信息类。除此之外，我们还可以使用单例解决资源访问冲突的问题。

线程池、日志对象，注册表、缓存



## 经典实现

### JDK

 `java.lang.Runtime` 的懒汉式单例模式

![image-20220724141104259](https://typora.xpp011.cn/typora/img/image-20220724141104259.png)





### Spring

`org.springframework.core.ReactiveAdapterRegistry`，懒汉式(double check)的单例模式

![image-20220724141351036](https://typora.xpp011.cn/typora/img/image-20220724141351036.png)





## 饿汉模式（Eager Initialization）

在类加载的时候，instance 静态实例就已经创建并初始化好了，所以，instance 实例的创建过程是线程安全的。不过，这样的实现方式不支持延迟加载（在真正用到 IdGenerator 的时候，再创建实例）

```java
public class EagerSingleton {

    private static final EagerSingleton instance =  new EagerSingleton();;

    private EagerSingleton() {}

    public static EagerSingleton getInstance() {
        return instance;
    }
}
```



****



## 懒汉模式（Lazy Initialization）

在编程中，延迟初始化是一种编程技巧。在第一次需要时，才创建对象、计算值或者执行其他耗时的过程。

使用`double check`的方法可以减缓锁竞争的问题，增加单例模式的并发度

```java
class LazySingleton {

    private static volatile LazySingleton instance;

    private LazySingleton() {
    }

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }


    /**
     * double check
     * @return
     */
    public static LazySingleton getInstanceDoubleCheck() {
        if (instance == null) {
            //此处加synchronized减少锁竞争
            synchronized (LazySingleton.class) {
                //避免等待线程进入时重新创建对象
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

}
```



### **volatile思考** 

`double check`的方式中，instance一定是加了`volatile`，其目的就是防止指令重排。

创建一个对象简单来说分为以下几步:

1. 判断是否类被虚拟机加载（未被加载则先走类加载逻辑）
2. 类加载后，确定类大小，向堆上申请空间（指针碰撞和空闲表）
3. 对堆上内存进行初始化零值
4. 设置对象头（类元数据地址，GC年龄，hashCocde）
5. 调用构造函数<init>
6. 地址赋给外部引用

对于以上步骤，java会进行指令重排提高处理速度，**即其他线程拿到对象地址时，该对象还未初始化完成**



**temporary variable**

接下来我们看这样一份代码:

```java
    /**
     * temporary variable
     * @return
     */
    public static LazySingleton getInstanceTemp() {
        LazySingleton temp = instance;
        if (temp == null) {
            synchronized (LazySingleton.class) {
                temp = instance;
                if (temp == null) {
                    temp = new LazySingleton();
                    instance = temp;
                }
            }
        }
        return temp;
    }
```

这种使用临时变量的单例模式经常出现在源码中

如Spring源码的`ReactiveAdapterRegistry`

![image-20220721225723807](https://typora.xpp011.cn/typora/img/image-20220721225723807.png)

这种使用临时变量访问`instance`有什么好处呢

其实它可以减少一次访问主存的机会，**一次是判断null的时候，一次方法返回值的时候**，在高并发场景减少一次访问主存带来的收益相当高，那么使用临时变量的方式就只会访问一次主存啦

参考： https://www.javacodemonk.com/threadsafe-singleton-design-pattern-java-806ad7e6



****





## 静态内部类  （Static Inner Initialization）

`InnerSingleton`是一个静态内部类，当外部类 `StaticInnerSingleton`被加载的时候，并不会创建 `InnerSingleton`实例对象。只有当调用 getInstance() 方法时，`InnerSingleton`才会被加载，这个时候才会创建 instance。instance 的唯一性、创建过程的线程安全性，都由 JVM 来保证。所以，这种实现方法既保证了线程安全，又能做到延迟加载。

```java
class StaticInnerSingleton {

    private StaticInnerSingleton() {}

    private static class InnerSingleton {
        private static final StaticInnerSingleton instance = new StaticInnerSingleton();
    }

    public static StaticInnerSingleton getInstance() {
        return InnerSingleton.instance;
    }

}
```







****



## 枚举 （Enum  Initialization）

枚举类实现单例模式和其他没有什么区别，只不过是class变成了enum

使用枚举实现单例的优势是无法被破环，具有强力的壁垒，具体的可以看下面是如何破环单例模式的

首先是反射，枚举类无法被反射，这是java的特性

其次是序列化，枚举是使用valueOf()方法序列化的，所以保证了不会在序列化过程中创建新对象

```java
enum EnumSingleton {
    INSTANCE;

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}
```





## 破坏单例模式

### 反射

反射不用多讲了，直接反射创建单例类的一个实例即可

```java
static void reflectiveBreak () throws Exception {
        EagerSingleton right = EagerSingleton.getInstance();
        Class<EagerSingleton> clzss = EagerSingleton.class;
        Constructor<EagerSingleton> constructor = clzss.getDeclaredConstructor();
        constructor.setAccessible(true);
        EagerSingleton instance = constructor.newInstance();
        System.out.println(instance == right);
    }
```





### 序列化

序列化是破坏单例模式的一大利器。其与克隆性质有些相似，需要类实现序列化接口，相比于克隆，实现序列化在实际操作中更加不可避免，有些类，它就是一定要序列化。



```java
    static void serializableBreak() throws Exception {
        EagerSingleton instance = EagerSingleton.getInstance();
        String name = "filePath";
        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(name));
        writer.writeObject(instance);

        ObjectInputStream read = new ObjectInputStream(new FileInputStream(name));
        EagerSingleton eagerSingleton = (EagerSingleton) read.readObject();
        System.out.println(instance == eagerSingleton);
    }
```



ObjectInputStream其内部的`readOrdinaryObject`方法也是使用了反射创建了一个实例

![image-20220721235816189](https://typora.xpp011.cn/typora/img/image-20220721235816189.png)
