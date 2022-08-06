package PrototypeDesignPattern;

import java.io.*;
import java.util.Objects;

/**
 * @program: design-patterns
 * @description: 原型模式
 * @author: xpp011
 * @create: 2022-08-06 22:06
 **/

public class PrototypePattern implements Cloneable, Serializable {

    public static void main(String[] args) {
        PrototypePattern prototypePattern = new PrototypePattern("张三", (short) 18, new Word());
        System.out.println("==========递归复制==========");
        PrototypePattern recursionClone = (PrototypePattern) prototypePattern.recursionClone();
        System.out.println("memory address: " + (prototypePattern == recursionClone));
        System.out.println("equals: " + prototypePattern.equals(recursionClone));
        System.out.println("hashCode: " + (prototypePattern.hashCode() == recursionClone.hashCode()));

        System.out.println("==========序列化复制==========");
        PrototypePattern serializableClone = (PrototypePattern) prototypePattern.serializableClone();
        System.out.println("memory address: " + (prototypePattern == serializableClone));
        System.out.println("equals: " + prototypePattern.equals(serializableClone));
        System.out.println("hashCode: " + (prototypePattern.hashCode() == serializableClone.hashCode()));
    }

    private String name;

    private Short age;

    private Word word;

    public PrototypePattern(String name, Short age, Word word) {
        this.name = name;
        this.age = age;
        this.word = word;
    }

    public Object recursionClone() {
        //递归复制对象，复制基础对象以及String
        PrototypePattern recursionClone = new PrototypePattern(name, age, (Word) word.clone());
        return recursionClone;
    }

    public Object serializableClone() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrototypePattern that = (PrototypePattern) o;
        return Objects.equals(name, that.name) && Objects.equals(age, that.age) && Objects.equals(word, that.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, word);
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

    @Override
    public boolean equals(Object o1) {
        if (this == o1) return true;
        if (o1 == null || getClass() != o1.getClass()) return false;
        Word word = (Word) o1;
        return Objects.equals(o, word.o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(o);
    }
}