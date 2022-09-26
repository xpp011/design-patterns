package ObserverDesignPattern.demo;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息总线
 *
 * @author: xpp011 2022-09-22 23:03
 **/

public class EventBus {

    private ObserverRegistry observerRegistry = new ObserverRegistry();

    public void register(Object object) {
        if (object == null) throw new IllegalArgumentException();
        observerRegistry.register(object);
    }

    public void unregister(Object object) {
        if (object == null) throw new IllegalArgumentException();
        observerRegistry.unregister(object);
    }

    public void post(Object event) {
        List<ObserverAction> actions = observerRegistry.getMatchedObserverActions(event);
        execute(actions, event);
    }

    protected void execute(List<ObserverAction> actions, Object event) {
        actions.forEach(v -> v.execute(event));
    }
}
