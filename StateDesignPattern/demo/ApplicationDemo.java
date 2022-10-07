package StateDesignPattern.demo;

import ObserverDesignPattern.demo.EventBus;
import StateDesignPattern.demo.event.FireFlowerEvent;
import StateDesignPattern.demo.event.Mario;
import StateDesignPattern.demo.event.MeetMonsterEvent;
import StateDesignPattern.demo.event.MushRoomEvent;

/**
 * @author: xpp011 2022-10-07 23:22
 **/

public class ApplicationDemo {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        Mario mario = new Mario();
        eventBus.register(new MarioStateMachine(mario));
        System.out.println(mario);
        eventBus.post(new FireFlowerEvent());
        System.out.println(mario);
        eventBus.post(new MeetMonsterEvent());
        System.out.println(mario);
        eventBus.post(new MushRoomEvent());
        System.out.println(mario);
    }

}
