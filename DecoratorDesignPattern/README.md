# 装饰器模式（DecoratorDesignPattern）



## 定义

装饰模式（Decorator）：动态地给一个对象添加一些额外的职责，就增加功能来说，装饰模式比生成子类更为灵活。



## 经典实现

java-IO流

<img src="https://typora.xpp011.cn/typora/img/image-20220809233015447.png" alt="image-20220809233015447"  />





## 解析

```java

public abstract class InputStream {
  //...
  public int read(byte b[]) throws IOException {
    return read(b, 0, b.length);
  }
  
  public int read(byte b[], int off, int len) throws IOException {
    //...
  }
  
  public long skip(long n) throws IOException {
    //...
  }

  public int available() throws IOException {
    return 0;
  }
  
  public void close() throws IOException {}

  public synchronized void mark(int readlimit) {}
    
  public synchronized void reset() throws IOException {
    throw new IOException("mark/reset not supported");
  }

  public boolean markSupported() {
    return false;
  }
}


public class FileInputStream extends InputStream {
  	public FileInputStream(String name) throws FileNotFoundException {
        this(name != null ? new File(name) : null);
    }
  
  	//.....
}


public class FilterInputStream extends InputStream {
  	protected volatile InputStream in;
  
  	public int read() throws IOException {
        return in.read();
    }
  
  	//default
}

public class BufferedInputStream extends FilterInputStream {

  protected BufferedInputStream(InputStream in) {
    super(in);
  }
  
  //...实现基于缓存的读数据接口...  
}

public class DataInputStream extends FilterInputStream {

  protected DataInputStream(InputStream in) {
    super(in);
  }
  
  //...实现读取基本类型数据的接口
}
```

以上是关于`InputStream`相关类的简要代码，详细可看JDK源码

```java
public static void main(String[] args) throws FileNotFoundException {
      InputStream fileInputStream = new FileInputStream("/a.txt");
      //增强缓冲读
      InputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
      //增强读取基本类型
      InputStream dataInputStream = new DataInputStream(bufferedInputStream);

  }
```



从使用的角度来说，感觉装饰器就是简单的使用“组合代替继承”，但是装饰器较于简单的组合关系，还有两个比较特殊的地方，

**第一个比较特殊的地方是：装饰器类和原始类继承同样的父类**，这样我们可以对原始类“嵌套”多个装饰器类。比如上方的代码，我们对 FileInputStream 嵌套了两个装饰器类：BufferedInputStream 和 DataInputStream，让它既支持缓存读取，又支持按照基本数据类型来读取数据。



**第二个比较特殊的地方是：装饰器类是对功能的增强，这也是装饰器模式应用场景的一个重要特点。**

实际上，符合“组合关系”这种代码结构的设计模式有很多，比如之前讲过的代理模式、桥接模式，还有现在的装饰器模式。尽管它们的代码结构很相似，但是每种设计模式的意图是不同的。就拿比较相似的代理模式和装饰器模式来说吧，**代理模式中，代理类附加的是跟原始类无关的功能，而在装饰器模式中，装饰器类附加的是跟原始类相关的增强功能。**



## FileInputStream

从源码中看`BufferedInputStream`和`DataInputStream` 和`FileInputStream`并非继承同一个类

一个是`FilterInputStream`，一个是`InputStream`，那是出于什么设计意图要这样设计呢

重新来审视这三个类你会发现，它们所负的职责不一样，`FileInputStream`可以看作是`InputStream`的某一种实现，**是从哪里读取数据这个维度的实现**，除了从文件读还有从字节流读取。

而`BufferedInputStream`和`DataInputStream` 可以看作是对读取的一种增强，**是从增强读取功能的维度触发的**，它们为了确保读取到数据需要将读取的操作委托给传递进来的 InputStream 对象来完成，而自己则对读取操作进行增强，那么委托的话就必须重写`read`方法，

```java

public class BufferedInputStream extends InputStream {
  protected volatile InputStream in;

  protected BufferedInputStream(InputStream in) {
    this.in = in;
  }
  
  // f()函数不需要增强，只是重新调用一下InputStream in对象的f()
  public void f() {
    in.f();
  }  
}
```

那么相应的避免代码重复就需要抽象出来一个装饰器父类`FileInputStream`

```java

public class FilterInputStream extends InputStream {
  protected volatile InputStream in;

  protected FilterInputStream(InputStream in) {
    this.in = in;
  }

  public int read() throws IOException {
    return in.read();
  }

  public int read(byte b[]) throws IOException {
    return read(b, 0, b.length);
  }
   
  public int read(byte b[], int off, int len) throws IOException {
    return in.read(b, off, len);
  }

  public long skip(long n) throws IOException {
    return in.skip(n);
  }

  public int available() throws IOException {
    return in.available();
  }

  public void close() throws IOException {
    in.close();
  }

  public synchronized void mark(int readlimit) {
    in.mark(readlimit);
  }

  public synchronized void reset() throws IOException {
    in.reset();
  }

  public boolean markSupported() {
    return in.markSupported();
  }
}
```



**总结**

装饰器模式主要解决继承关系过于复杂的问题，通过组合来替代继承。它主要的作用是给原始类添加增强功能。这也是判断是否该用装饰器模式的一个重要的依据。除此之外，装饰器模式还有一个特点，那就是可以对原始类嵌套使用多个装饰器。为了满足这个应用场景，在设计的时候，装饰器类需要跟原始类继承相同的抽象类或者接口。



## 装饰器模式与代理模式的区别

看完上面你会发现装饰器模式和代理模式非常相像，都是对原始功能的增强，并且和桥接模式和代理模式一样通过组合关系的方式来降低类的复杂度

其实代理模式和装饰器模式最主要的区别还是取决于设计者的意图，如果设计者的意图是隐藏实现细节，使用者不需要关心具体增强了什么的功能，也就是说使用者只能使用代理类(proxy)，而装饰器模式是需要使用者自己觉得需不需要使用增强功能，就像缓冲读取(BufferedInputStream)，使用者自行觉得是否使用缓冲读取，那么这种就是装饰器模式。