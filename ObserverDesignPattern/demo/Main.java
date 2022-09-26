package ObserverDesignPattern.demo;

/**
 * @author: xpp011 2022-09-26 22:38
 **/

public class Main {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new Listener());
        eventBus.post(new Object());
        eventBus.post("eventBus");
    }

}

class Listener {

    @Subscribe
    public void hello1(Object msg) {
        System.out.println("hello1! " + msg);
    }

    @Subscribe
    public void hello2(String msg) {
        System.out.println("hello2! " + msg);
    }

}