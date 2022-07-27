package FactoryDesignPattern.demo.entity;

/**
 * @program: design-patterns
 * @description:
 * @author: xpp011
 * @create: 2022-07-27 23:27
 **/

public class B {

    private String nickName;

    private A a;

    public B(String nickName, A a) {
        this.nickName = nickName;
        this.a = a;
    }
}
