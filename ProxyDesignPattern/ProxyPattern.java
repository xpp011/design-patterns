package ProxyDesignPattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: design-patterns
 * @description: 代理模式
 * @author: xpp011
 * @create: 2022-08-07 21:58
 **/

public class ProxyPattern {
    public static void main(String[] args) {
        Client client = new Client();
        IClient proxy = (IClient) new ClientProxy(client).getProxy();
        proxy.login();
    }
}

interface IClient {
    void login();
}

class Client implements IClient{

    @Override
    public void login() {
        System.out.println("登录啦");
    }
}


class ClientProxy {

    private Object proxyObject;

    public ClientProxy(Object proxyObject) {
        this.proxyObject = proxyObject;
    }

    public Object getProxy() {
        DynamicProxyHandler proxyHandle = new DynamicProxyHandler(proxyObject);
        Object proxyInstance = Proxy.newProxyInstance(proxyObject.getClass().getClassLoader(), proxyObject.getClass().getInterfaces(), proxyHandle);
        return proxyInstance;
    }

    class DynamicProxyHandler implements InvocationHandler {

        private Object proxyObject;

        public DynamicProxyHandler(Object proxyObject) {
            this.proxyObject = proxyObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(method.getName());
            System.out.println("被代理啦啦啦");
            Object res = method.invoke(proxyObject, args);
            return res;
        }
    }
}