# 原型模式（Prototype Design Pattern）





## 定义

*Create objects based on a template of an existing object through cloning.* 使用克隆在已有对象的基础上创建对象。





## 经典实现

**JDK**

java的集合类的clone都是原型模式的经典实现，只要继承了`Cloneable`接口的都具备可复制含义

<img src="https://typora.xpp011.cn/typora/img/image-20220806225747258.png" alt="image-20220806225747258" style="zoom:50%;" />



**Spring**

BeanUtils





## 实际应用

在实际使用原型模式时，要注意**深拷贝和浅拷贝问题**，深拷贝对象时，注意相应的成员变量也需要深拷贝直至一些基础类型

下面演示一下深拷贝的两种方式



### 递归复制对象

```java
public class PrototypeDesignPattern implements Cloneable, Serializable {

    private String name;

    private Short age;

    private Word word;

    public PrototypeDesignPattern(String name, Short age, Word word) {
        this.name = name;
        this.age = age;
        this.word = word;
    }

    public Object clone() {
        //递归复制对象，复制基础对象以及String
        PrototypeDesignPattern recursionClone = new PrototypeDesignPattern(name, age, (Word) word.clone());
    }
}

class Word implements Cloneable, Serializable {

    public Object o;

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
```





### 序列化复制对象

```java
    public Object clone() {

        //序列化复制对象
        ByteArrayOutputStream byteOutput = null;
        ObjectOutputStream out = null;
        ByteArrayInputStream byteIn = null;
        ObjectInputStream in = null;
        try {
            byteOutput = new ByteArrayOutputStream();
            out = new ObjectOutputStream(byteOutput);
            out.writeObject(this);

            byteIn = new ByteArrayInputStream(byteOutput.toByteArray());
            in = new ObjectInputStream(byteIn);
            return in.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (byteOutput != null) byteOutput.close();
                if (out != null) out.close();
                if (byteIn != null) byteIn.close();
                if (in != null) in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
```





![image-20220806232920795](https://typora.xpp011.cn/typora/img/image-20220806232920795.png)