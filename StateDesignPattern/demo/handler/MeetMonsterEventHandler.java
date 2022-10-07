package StateDesignPattern.demo.handler;

import StateDesignPattern.demo.State;
import StateDesignPattern.demo.event.Mario;
import StateDesignPattern.demo.event.MeetMonsterEvent;

/**
 * 遇到怪物事件处理器
 *
 * @author: xpp011 2022-10-07 22:31
 **/

public class MeetMonsterEventHandler extends EventHandler<MeetMonsterEvent> {

    @Override
    public boolean doHandler(Mario mario, MeetMonsterEvent event) {
        int penaltyScore = 0;
        if (State.CAPE.equals(mario.getState())) {
            penaltyScore = CapeEventHandler.SCORE;
        }
        if (State.SUPER.equals(mario.getState())) {
            penaltyScore = MushRoomEventHandler.SCORE;
        }
        if (State.FIRE.equals(mario.getState())) {
            penaltyScore = FireFlowerEventHandler.SCORE;
        }
        mario.incScore(-penaltyScore);
        mario.setCurrentState(State.SMALL);
        return true;
    }
}
