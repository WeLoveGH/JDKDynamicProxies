package com.godtrue.DynamicallyProxies.InterceptMethodCallsUsingDynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description： 演示拦截器的实现原理，在某个方法的调用前或调用后，需要做某些操作
 * @author：qianyingjie1
 * @create：2019-11-04
 */
public class MyInterceptor<T> implements InvocationHandler {

    private T t;

    public MyInterceptor(T t) {
        this.t = t;
    }


    /**
     * Processes a method invocation on a proxy instance and returns
     * the result.  This method will be invoked on an invocation handler
     * when a method is invoked on a proxy instance that it is
     * associated with.
     *
     * @param   proxy the proxy instance that the method was invoked on
     *
     * @param   method the {@code Method} instance corresponding to
     * the interface method invoked on the proxy instance.  The declaring
     * class of the {@code Method} object will be the interface that
     * the method was declared in, which may be a superinterface of the
     * proxy interface that the proxy class inherits the method through.
     *
     * @param   args an array of objects containing the values of the
     * arguments passed in the method invocation on the proxy instance,
     * or {@code null} if interface method takes no arguments.
     * Arguments of primitive types are wrapped in instances of the
     * appropriate primitive wrapper class, such as
     * {@code java.lang.Integer} or {@code java.lang.Boolean}.
     *
     * @return  the value to return from the method invocation on the
     * proxy instance.  If the declared return type of the interface
     * method is a primitive type, then the value returned by
     * this method must be an instance of the corresponding primitive
     * wrapper class; otherwise, it must be a type assignable to the
     * declared return type.  If the value returned by this method is
     * {@code null} and the interface method's return type is
     * primitive, then a {@code NullPointerException} will be
     * thrown by the method invocation on the proxy instance.  If the
     * value returned by this method is otherwise not compatible with
     * the interface method's declared return type as described above,
     * a {@code ClassCastException} will be thrown by the method
     * invocation on the proxy instance.
     *
     * @throws  Throwable the exception to throw from the method
     * invocation on the proxy instance.  The exception's type must be
     * assignable either to any of the exception types declared in the
     * {@code throws} clause of the interface method or to the
     * unchecked exception types {@code java.lang.RuntimeException}
     * or {@code java.lang.Error}.  If a checked exception is
     * thrown by this method that is not assignable to any of the
     * exception types declared in the {@code throws} clause of
     * the interface method, then an
     * {@link UndeclaredThrowableException} containing the
     * exception that was thrown by this method will be thrown by the
     * method invocation on the proxy instance.
     *
     * @see     UndeclaredThrowableException
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before method call : " + method.getName());
        Object result = method.invoke(t, args);
        System.out.println("after method call : " + method.getName());
        return result;
    }

    /**
     * 获取自动生成的代理类实例
     * @param t 实现接口的类
     * @param interfaceType 接口
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getProxy(T t, Class<? super T> interfaceType) {

        /**
         * 构建自定义的拦截器
         */
        MyInterceptor handler = new MyInterceptor(t);

        /**
         * 通过JDK的代理类，获取我们想要的动态代理类的实例，核心
         */

        /**
         * Returns an instance of a proxy class for the specified interfaces
         * that dispatches method invocations to the specified invocation
         * handler.
         *
         * <p>{@code Proxy.newProxyInstance} throws
         * {@code IllegalArgumentException} for the same reasons that
         * {@code Proxy.getProxyClass} does.
         *
         * @param   loader the class loader to define the proxy class
         * @param   interfaces the list of interfaces for the proxy class
         *          to implement
         * @param   h the invocation handler to dispatch method invocations to
         * @return  a proxy instance with the specified invocation handler of a
         *          proxy class that is defined by the specified class loader
         *          and that implements the specified interfaces
         * @throws  IllegalArgumentException if any of the restrictions on the
         *          parameters that may be passed to {@code getProxyClass}
         *          are violated
         * @throws  SecurityException if a security manager, <em>s</em>, is present
         *          and any of the following conditions is met:
         *          <ul>
         *          <li> the given {@code loader} is {@code null} and
         *               the caller's class loader is not {@code null} and the
         *               invocation of {@link SecurityManager#checkPermission
         *               s.checkPermission} with
         *               {@code RuntimePermission("getClassLoader")} permission
         *               denies access;</li>
         *          <li> for each proxy interface, {@code intf},
         *               the caller's class loader is not the same as or an
         *               ancestor of the class loader for {@code intf} and
         *               invocation of {@link SecurityManager#checkPackageAccess
         *               s.checkPackageAccess()} denies access to {@code intf};</li>
         *          <li> any of the given proxy interfaces is non-public and the
         *               caller class is not in the same {@linkplain Package runtime package}
         *               as the non-public interface and the invocation of
         *               {@link SecurityManager#checkPermission s.checkPermission} with
         *               {@code ReflectPermission("newProxyInPackage.{package name}")}
         *               permission denies access.</li>
         *          </ul>
         * @throws  NullPointerException if the {@code interfaces} array
         *          argument or any of its elements are {@code null}, or
         *          if the invocation handler, {@code h}, is
         *          {@code null}
         */

        return (T) Proxy.newProxyInstance(interfaceType.getClassLoader(),
                new Class<?>[]{interfaceType}, handler
        );
    }
}

