package AdapterDesignPattern;

/**
 * @program: design-patterns
 * @description: 适配器模式
 * @author: xpp011
 * @create: 2022-08-11 22:13
 **/

public class AdapterPattern {

    public static void main(String[] args) {

    }

}


interface ITarget {
    void f1();

    void f2();

    void fc();
}

class Adaptee {
    public void fa() {
    }

    public void fb() {
    }

    public void fc() {
    }
}

//类适配模式: 基于继承实现
class AdaptorClass extends Adaptee implements ITarget {

    @Override
    public void f1() {
        //适配Adaptee的fa方法
    }

    @Override
    public void f2() {
        //适配Adaptee的fb方法
    }
}

//对象适配模式: 基于组合实现
class AdaptorObject implements ITarget {

    private Adaptee adaptee;

    public AdaptorObject(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void f1() {
        adaptee.fa();
    }

    @Override
    public void f2() {
        adaptee.fb();
    }

    @Override
    public void fc() {
        adaptee.fc();
    }
}