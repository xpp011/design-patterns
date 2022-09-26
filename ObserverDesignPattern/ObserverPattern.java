package ObserverDesignPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 *
 * @author: xpp011 2022-09-21 22:22
 **/

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
