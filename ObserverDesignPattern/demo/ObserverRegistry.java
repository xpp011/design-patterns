package ObserverDesignPattern.demo;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 注册表
 *
 * @author: xpp011 2022-09-26 23:18
 **/

public class ObserverRegistry {

    private Map<Class, CopyOnWriteArraySet<ObserverAction>> registry = new ConcurrentHashMap<>();

    public void register(Object observer) {
        Map<Class<?>, Collection<ObserverAction>> annotatedMethods = findAllObserverActions(observer);
        for (Map.Entry<Class<?>, Collection<ObserverAction>> entry : annotatedMethods.entrySet()) {
            Class<?> parameterType = entry.getKey();
            CopyOnWriteArraySet<ObserverAction> observerActions = registry.get(parameterType);
            if (observerActions == null) {
                registry.putIfAbsent(parameterType, new CopyOnWriteArraySet<>());
                observerActions = registry.get(parameterType);
            }
            observerActions.addAll(entry.getValue());
        }
    }

    public void unregister(Object observer) {

    }

    public List<ObserverAction> getMatchedObserverActions(Object event) {
        List<ObserverAction> matchedObservers = new ArrayList<>();
        for (Map.Entry<Class, CopyOnWriteArraySet<ObserverAction>> entry : registry.entrySet()) {
            Class key = entry.getKey();
            if (key.isAssignableFrom(event.getClass())) {
                matchedObservers.addAll(entry.getValue());
            }
        }
        return matchedObservers;
    }

    private Map<Class<?>, Collection<ObserverAction>> findAllObserverActions(Object observer) {
        Map<Class<?>, Collection<ObserverAction>> observerActions = new HashMap<>();
        List<Method> annotatedMethods = getAnnotatedMethods(observer.getClass());
        for (Method method : annotatedMethods) {
            Class<?> parameterType = method.getParameterTypes()[0];
            if (!observerActions.containsKey(parameterType)) {
                observerActions.put(parameterType, new ArrayList<>());
            }
            observerActions.get(parameterType).add(new ObserverAction(observer, method));
        }
        return observerActions;
    }

    private List<Method> getAnnotatedMethods(Class<?> observerClass) {
        List<Method> annotatedMethods = new ArrayList<>();
        Method[] methods = observerClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Subscribe.class)) {
                int count = method.getParameterCount();
                if (count > 1 || count == 0) {
                    throw new RuntimeException(method.getName() + "监听方法参数异常");
                }
                annotatedMethods.add(method);
            }
        }
        return annotatedMethods;
    }

}
