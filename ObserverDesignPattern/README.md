# 观察者模式（Observer Design Pattern）



## 定义

观察者模式：

> Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
>
> 在对象之间定义一个一对多的依赖，当一个对象状态改变的时候，所有依赖的对象都会自动收到通知。



## 经典实现

### Spring

使用Spring框架自带的`ApplicationEvent`可轻松实现观察者模式，使用`@Async`控制异步非堵塞和同步堵塞模式





## 实际使用



```java

public class ObserverPattern {

    public static void main(String[] args) {
        Subject<Message> subject = new ConcreteSubject();
        subject.registerObserver(new ConcreteObserverOne());
        subject.registerObserver(new ConcreteObserverTwo());
        subject.notifyObserver(new Message("hello! Observer Design Pattern"));
    }

}

abstract class  Event {

}

class Message extends Event {
    String message;

    public Message(String message) {
        this.message = message;
    }
}

interface Observer<E extends Event> {
    void update(E event);
}

class ConcreteObserverOne implements Observer<Message> {
    @Override
    public void update(Message event) {
        System.out.println("事件处理器1: " + event.message);
    }
}

class ConcreteObserverTwo implements Observer<Message> {
    @Override
    public void update(Message event) {
        System.out.println("事件处理器2: " + event.message);
    }
}

interface Subject<E extends Event> {
    void registerObserver(Observer<E> observer);

    void removeObserver(Observer<E> observer);

    void notifyObserver(E event);
}

class ConcreteSubject implements Subject<Message> {

    private List<Observer<Message>> observers = new ArrayList();

    @Override
    public void registerObserver(Observer<Message> observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer<Message> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(Message event) {
        for (Observer<Message> observer : observers) {
            observer.update(event);
        }
    }
}

```

观察者模式可以很好的将两段不相关的代码解耦，如果一段代码需要频繁的修改，同时与另一段代码毫不相干，那么就适合观察者模式

比如：注册完成后需要将注册信息发送大数据，过了一段事件又要发送优惠卷等等，就可以将频繁修改代码使用观察者模式解耦