package FactoryDesignPattern.demo.croe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: design-patterns
 * @description: bean元数据
 * @author: xpp011
 * @create: 2022-07-27 23:37
 **/

public class BeanDefinition {

    private String className;

    private String id;

    private List<ConstructorArg> args = new ArrayList<>();

    private Scope scope = Scope.SINGLETON;

    private boolean lazyInit = false;

    public BeanDefinition(String className, String id, ConstructorArg... args) {
        this.className = className;
        this.id = id;
        this.args = Arrays.asList(args);
    }

    public String getClassName() {
        return className;
    }

    public String getId() {
        return id;
    }

    public List<ConstructorArg> getArgs() {
        return args;
    }

    public boolean isSingleton() {
        return Scope.SINGLETON.equals(scope);
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public enum Scope { SINGLETON, PROTOTYPE }

    public static class ConstructorArg {
        private boolean isRef;

        private Class<?> type;

        private Object arg;

        public ConstructorArg(boolean isRef, Class<?> type, Object arg) {
            this.isRef = isRef;
            this.type = type;
            this.arg = arg;
        }

        public boolean isRef() {
            return isRef;
        }

        public Class<?> getType() {
            return type;
        }
        public Object getArg() {
            return arg;
        }

    }

}
