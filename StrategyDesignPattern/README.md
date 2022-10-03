# 策略模式（Strategy Design Pattern）



## 定义

> Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from clients that use it.
>
> 定义一族算法类，将每个算法分别封装起来，让它们可以互相替换。

策略模式可以使算法的变化独立于使用它们的客户端



## 经典实现

### java

**Comparator** 

传入不同的比较器，实现不同比较效果

![image-20221003220421884](https://typora.xpp011.cn/typora/img/image-20221003220421884.png)



### Spring

**Resource**

不同的**Resource**实现

![image-20221003220659603](https://typora.xpp011.cn/typora/img/image-20221003220659603.png)





## 实际应用

实现一个对文件排序系统，文件全部由正整数组成，逗号分隔

```java
public class Sorter {

    public void sorterFile(File file) {
        long size = file.length();
        ISortAlg sortAlg = SortAlgFactory.getSortAlg(size);
        sortAlg.sort(file);
    }

}

public class SortAlgFactory {

    private static long GB = 1000 * 1000 * 1000;

    public static ISortAlg getSortAlg(long size) {
        if (size < 6 * GB) {
            return new QuickSort();
        }
        if (size < 10 * GB) {
            return new ExternalSort();
        }
        if (size < 100 * GB) {
            return new ConcurrentExternalSort();
        }
        return new MapreduceSort();
    }

}

public interface ISortAlg {

    void sort(File file);

}

class QuickSort implements ISortAlg {
	....
}

class ExternalSort implements ISortAlg {
	....
}

class ConcurrentExternalSort implements ISortAlg {
	....
}

class MapreduceSort implements ISortAlg {
	....
}
```



## 区别

策略模式：

- 目的实现方便地替换不同的算法类
- 侧重于算法（行为）的实现



工厂模式：

- 目的是创建不同且相关的对象
- 侧重于"创建对象"

工厂模式主要是用于解决对象的创建和使用，降低对象创建的复杂度，而策略模式更加注重的是算法（行为）的定义、创建、使用。

策略模式可以使用工厂模式实现