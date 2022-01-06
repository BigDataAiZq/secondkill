package com.zjh.seckill.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

public class MyDynamicProxy {

    public static void main(String[] args) {
        Hello hello = new HelloImpl();

        MyInvocationHandler handler = new MyInvocationHandler(hello);
        // 构造代理实例
        Class[] cs = { Hello.class };
        Hello proxyHello = (Hello) Proxy.newProxyInstance(Hello.class.getClassLoader(), cs, handler);
        // 调用代理方法
        proxyHello.sayHello();

        System.out.println(proxyHello.getName());
    }

}

interface Hello {

    void sayHello();

    String getName();
}

class HelloImpl implements Hello {

    @Override
    public void sayHello() {
        System.out.println("hello world");
    }

    public String getName() {
        return "ztq" + new Date();
    }
}

class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 添加功能，比如限流、日志、事物等,此处只是一个打印
        System.out.println("Invoking start!");
        Object result = method.invoke(target, args);
        System.out.println("Invocation End!");
        return result;
    }
}