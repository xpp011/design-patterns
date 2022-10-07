package StateDesignPattern.demo.handler;

import StateDesignPattern.demo.State;
import StateDesignPattern.demo.event.Mario;
import StateDesignPattern.demo.event.MushRoomEvent;

/**
 * 蘑菇事件处理器
 *
 * @author: xpp011 2022-10-07 22:31
 **/

public class MushRoomEventHandler extends EventHandler<MushRoomEvent> {

    public final static int SCORE = 100;

    @Override
    public boolean doHandler(Mario mario, MushRoomEvent event) {
        if (State.SMALL.equals(mario.getState())) {
            mario.incScore(SCORE);
            mario.setCurrentState(State.SUPER);
        }
        return true;
    }
}
