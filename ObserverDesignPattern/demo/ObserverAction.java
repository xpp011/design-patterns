package ObserverDesignPattern.demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: xpp011 2022-09-26 23:18
 **/

public class ObserverAction {
    private Object target;

    private Method method;

    public ObserverAction(Object target, Method method) {
        this.target = target;
        this.method = method;
    }

    public void execute(Object param) {
        try {
            method.invoke(target, param);
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println(target.getClass().getName() + "target method exec failure:  " + e.getMessage());
        }
    }

    public Object getTarget() {
        return target;
    }

    public Method getMethod() {
        return method;
    }
}
