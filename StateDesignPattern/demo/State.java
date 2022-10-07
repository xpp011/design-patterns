package StateDesignPattern.demo;

/**
 * @author: xpp011 2022-10-07 22:08
 **/

public enum State {


    SMALL(0), SUPER(1), FIRE(2), CAPE(3);
    private int value;

    private State(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

}
