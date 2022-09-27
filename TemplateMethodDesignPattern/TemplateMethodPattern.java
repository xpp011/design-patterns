package TemplateMethodDesignPattern;

/**
 * 模板模式
 *
 * @author: xpp011 2022-09-27 22:35
 **/

public class TemplateMethodPattern {

    public static void main(String[] args) {

    }

}

abstract class AbstractClass {

    public void templateMethod() {
        method1();

        method2();
    }

    protected abstract void method1();

    protected abstract void method2();
}

class ConcreteClass1 extends  AbstractClass {
    @Override
    protected void method1() {
        System.out.println("hello1");
    }

    @Override
    protected void method2() {
        System.out.println("hello2");
    }
}


class ConcreteClass2 extends  AbstractClass {
    @Override
    protected void method1() {
        System.out.println("你好1");
    }

    @Override
    protected void method2() {
        System.out.println("你好2");
    }
}
