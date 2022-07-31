package FactoryDesignPattern.demo.exception;

/**
 * @program: design-patterns
 * @description:
 * @author: xpp011
 * @create: 2022-07-28 22:39
 **/

public class BeanClassNotMatchException extends RuntimeException {

    public BeanClassNotMatchException() {
    }

    public BeanClassNotMatchException(String message) {
        super(message);
    }

    public BeanClassNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanClassNotMatchException(Throwable cause) {
        super(cause);
    }

    public BeanClassNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

