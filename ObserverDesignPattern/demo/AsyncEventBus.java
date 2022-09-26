package ObserverDesignPattern.demo;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * 异步的消息总线
 *
 * @author: xpp011 2022-09-22 23:04
 **/

public class AsyncEventBus extends EventBus {

    private ExecutorService executors;

    public AsyncEventBus(ExecutorService executors) {
        this.executors = executors;
    }

    @Override
    protected void execute(List<ObserverAction> actions, Object event) {
        actions.forEach(v -> executors.submit(() -> v.execute(event)));
    }
}
