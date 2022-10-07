package StateDesignPattern.demo.handler;

import StateDesignPattern.demo.event.Event;
import StateDesignPattern.demo.event.Mario;

/**
 * 事件处理器
 *
 * @author: xpp011 2022-10-07 22:28
 **/

public abstract class EventHandler<T extends Event> {

    public boolean handler(Mario mario, Event event) {
        if (mario == null) {
            throw new IllegalArgumentException("mario is null");
        }
        if (event == null) {
            throw new IllegalArgumentException("event is null");
        }
        return doHandler(mario, (T) event);
    }

    protected abstract boolean doHandler(Mario mario, T event);

}
