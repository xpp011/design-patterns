package StateDesignPattern.demo.handler;

import StateDesignPattern.demo.State;
import StateDesignPattern.demo.event.FireFlowerEvent;
import StateDesignPattern.demo.event.Mario;

/**
 * 火焰事件处理器
 *
 * @author: xpp011 2022-10-07 22:30
 **/

public class FireFlowerEventHandler extends EventHandler<FireFlowerEvent> {

    public final static int SCORE = 300;

    @Override
    public boolean doHandler(Mario mario, FireFlowerEvent event) {
        if (State.SMALL.equals(mario.getState()) || State.SUPER.equals(mario.getState())) {
            mario.incScore(SCORE);
            mario.setCurrentState(State.FIRE);
        }
        return true;
    }
}
