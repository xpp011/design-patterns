package FactoryDesignPattern.demo.exception;

/**
 * @program: design-patterns
 * @description:
 * @author: xpp011
 * @create: 2022-07-28 23:06
 **/

public class BeanCreationFailureException extends RuntimeException{

    public BeanCreationFailureException() {
    }

    public BeanCreationFailureException(String message) {
        super(message);
    }

    public BeanCreationFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanCreationFailureException(Throwable cause) {
        super(cause);
    }

    public BeanCreationFailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
