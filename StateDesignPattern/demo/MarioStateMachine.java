package StateDesignPattern.demo;

import ObserverDesignPattern.demo.ObserverRegistry;
import ObserverDesignPattern.demo.Subscribe;
import StateDesignPattern.demo.event.*;
import StateDesignPattern.demo.handler.*;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 马里奥状态机
 *
 * @author: xpp011 2022-10-07 22:09
 **/


public class MarioStateMachine {

    private static final Logger log = LoggerFactory.getLogger(MarioStateMachine.class);

    public final static Map<Class<? extends Event>, EventHandler> handlerMap = new HashMap<>();

    static {
        handlerMap.put(FireFlowerEvent.class, new FireFlowerEventHandler());
        handlerMap.put(CapeEvent.class, new CapeEventHandler());
        handlerMap.put(MeetMonsterEvent.class, new MeetMonsterEventHandler());
        handlerMap.put(MushRoomEvent.class, new MushRoomEventHandler());
    }

    private Mario mario;

    public MarioStateMachine(Mario mario) {
        this.mario = mario;
    }

    /**
     * 事件监听器的实现不再重复写了，可以使用spring自带的观察者模式，
     * 也可以参考自己实现的观察者模式 {@link ObserverRegistry#register(Object)}
     *
     * @param event
     * @return
     */
    @Subscribe
    public boolean eventListener(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("event is null");
        }
        Class<? extends Event> clazz = event.getClass();
        if (handlerMap.containsKey(clazz)) {
            return handlerMap.get(clazz).handler(mario, event);
        } else {
            log.error("no handler found: " + clazz.getName());
        }
        return false;
    }

    public Mario getMario() {
        return mario;
    }
}