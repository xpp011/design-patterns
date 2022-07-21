package SingletonDesignPattern;


import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @program: design-patterns
 * @description: 单例模式
 * @author: xpp011
 * @create: 2022-07-20 23:18
 **/

public class Singleton {
    public static void main(String[] args)  throws Exception {
        reflectiveBreak();
        serializableBreak();
    }

    static void reflectiveBreak () throws Exception {
        EagerSingleton right = EagerSingleton.getInstance();
        Class<EagerSingleton> clzss = EagerSingleton.class;
        Constructor<EagerSingleton> constructor = clzss.getDeclaredConstructor();
        constructor.setAccessible(true);
        EagerSingleton instance = constructor.newInstance();
        System.out.println(instance == right);
    }

    static void serializableBreak() throws Exception {
        EagerSingleton instance = EagerSingleton.getInstance();
        String name = "D:\\win10桌面存放\\DesignPatterns\\design-patterns\\SingletonDesignPattern\\a.txt";
        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(name));
        writer.writeObject(instance);

        ObjectInputStream read = new ObjectInputStream(new FileInputStream(name));
        EagerSingleton eagerSingleton = (EagerSingleton) read.readObject();
        System.out.println(instance == eagerSingleton);
    }
}

/**
 * 饿汉式
 */
class EagerSingleton implements Serializable{

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

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}