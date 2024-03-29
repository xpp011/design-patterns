package FactoryDesignPattern.demo.exception;

/**
 * @program: design-patterns
 * @description:
 * @author: xpp011
 * @create: 2022-07-28 22:36
 **/

public class NoSuchBeanDefinitionException extends RuntimeException{
    public NoSuchBeanDefinitionException() {
        super();
    }

    public NoSuchBeanDefinitionException(String message) {
        super(message);
    }

    public NoSuchBeanDefinitionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchBeanDefinitionException(Throwable cause) {
        super(cause);
    }

    protected NoSuchBeanDefinitionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
