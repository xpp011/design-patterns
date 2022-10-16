package MementoDesignPattern.demo;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author: xpp011 2022-10-16 22:38
 **/

public class Context {

    private StringBuilder text;

    private Deque<Integer> uLog;

    public Context() {
        text = new StringBuilder();
        uLog = new LinkedList<>();
    }

    public String getText() {
        return text.toString();
    }

    public void append(String s) {
        Objects.requireNonNull(s);
        text.append(s);
        uLog.addLast(s.length());
    }

    public void undo() {
        if (uLog.isEmpty()) throw new RuntimeException("can't undo");
        int len = text.length();
        text.delete(len - uLog.pollLast(), len);
    }

}
