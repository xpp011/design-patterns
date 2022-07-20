# 单例模式(Singleton Design Pattern)



## 定义

**单例模式**是一种创建型设计模式， 让你能够保证一个类只有一个实例， 并提供一个访问该实例的全局节点。

如果你的代码能够访问单例类， 那它就能调用单例类的静态方法。 无论何时调用该方法， 它总是会返回相同的对象。



**哪些场景适合做单例？**

从业务概念上，有些数据在系统中只应该保存一份，就比较适合设计为单例类。比如，系统的配置信息类。除此之外，我们还可以使用单例解决资源访问冲突的问题。



## 经典实现







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





## 枚举 （Enum  Initialization）
