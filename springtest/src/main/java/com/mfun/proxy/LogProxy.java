package com.mfun.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class LogProxy<T> {
    public T getProxy(final T obj) {
        // 重点：InvocationHandler 定义调用代理对象方法时的具体操作
        InvocationHandler handler = new InvocationHandler() {
            /**
             * 处理方法调用
             * @param proxy 传入的代理对象，不要修改
             * @param method 调用的被代理对象的目标方法
             * @param args 调用参数
             * @return 执行结果
             * @throws Throwable 执行中的异常
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                try {
                    // 目标方法执行前的操作
                    // 例如，模拟日志记录
                    System.out.println("=============");
                    System.out.println("Method: " + method.getName() +
                            ", args: " + Arrays.toString(args));

                    // 调用实际的方法
                    Object result = method.invoke(obj, args);
                    // 目标方法执行后的操作
                    System.out.println("result:" + result);
                    System.out.println("=============");
                    // 必须将目标方法调用的返回值返回给调用者，保证代理对象的执行结果与被代理对象相同
                    return result;
                } catch (Exception e) {
                    // 抛出异常时执行的操作
                    System.out.println(e.getCause().getMessage());
                    return null;
                } finally {
                    // 执行清理操作
                }
            }
        };

        Class<?> aClass = obj.getClass();
        ClassLoader classLoader = aClass.getClassLoader();
        Class<?>[] interfaces = aClass.getInterfaces();
        System.out.println("inter: " + Arrays.toString(interfaces));

        // 注意，返回的对象只是与 obj 实现了相同的接口，而不是属于同一个类，代理对象属于代理类，例如 $Proxy10
        // classLoader：生成代理对象使用的类加载器
        // interfaces：代理对象实现的接口。例如，如果只代理 Calculator 接口的声明的方法，可传入 new Class[] {Calculator.class}
        // handler：调用处理器
        return (T) Proxy.newProxyInstance(classLoader, interfaces, handler);
    }

}
