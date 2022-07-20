package SingletonDesignPattern;

/**
 * @program: design-patterns
 * @description: 单例模式
 * @author: xpp011
 * @create: 2022-07-20 23:18
 **/

public class Singleton {
    public static void main(String[] args) {

    }
}

/**
 * 饿汉式
 */
class EagerSingleton {

    private static final EagerSingleton instance = new EagerSingleton();
    ;

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}

/**
 * 懒汉式
 */
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

/**
 * 静态内部类式
 */
class StaticInnerSingleton {

    private StaticInnerSingleton() {}

    private static class InnerSingleton {
        private static final StaticInnerSingleton instance = new StaticInnerSingleton();
    }

    public static StaticInnerSingleton getInstance() {
        return InnerSingleton.instance;
    }

}


/**
 * 枚举类型
 */

enum EnumSingleton {
    INSTANCE;
    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }
}