package StateDesignPattern.demo.handler;

import StateDesignPattern.demo.State;
import StateDesignPattern.demo.event.CapeEvent;
import StateDesignPattern.demo.event.Mario;

/**
 * 斗篷事件处理器
 *
 * @author: xpp011 2022-10-07 22:29
 **/

public class CapeEventHandler extends EventHandler<CapeEvent> {

    public final static int SCORE = 200;

    @Override
    public boolean doHandler(Mario mario, CapeEvent event) {
        if (State.SMALL.equals(mario.getState()) || State.SUPER.equals(mario.getState())) {
            mario.incScore(SCORE);
            mario.setCurrentState(State.CAPE);
        }
        return true;
    }
}
