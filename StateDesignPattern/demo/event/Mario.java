package StateDesignPattern.demo.event;

import StateDesignPattern.demo.State;

import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 马里奥
 *
 * @author: xpp011 2022-10-07 22:19
 **/

public class Mario {

    private AtomicInteger score;

    private AtomicReference<State> currentState;

    public Mario() {
        score = new AtomicInteger(0);
        currentState = new AtomicReference<>(State.SMALL);
    }

    public int incScore(int amount) {
        return score.addAndGet(amount);
    }

    public void setCurrentState(State state) {
        currentState.set(state);
    }

    public int getScore() {
        return score.get();
    }

    public State getState() {
        return currentState.get();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Mario.class.getSimpleName() + "[", "]")
                .add("score=" + score)
                .add("currentState=" + currentState)
                .toString();
    }
}
