package com.godtrue.DynamicallyProxies.DynamicallyImplementAnInterface;

import com.godtrue.DynamicallyProxies.SaveProxyByteCodeUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @description： 演示动态代理的实现以及本质
 * @author：qianyingjie1
 * @create：2019-11-04
 */
public class MyInvocationHandlerExample implements InvocationHandler {
    /**
     * Processes a method invocation on a proxy instance and returns
     * the result.  This method will be invoked on an invocation handler
     * when a method is invoked on a proxy instance that it is
     * associated with.
     *
     * @param proxy  the proxy instance that the method was invoked on
     * @param method the {@code Method} instance corresponding to
     *               the interface method invoked on the proxy instance.  The declaring
     *               class of the {@code Method} object will be the interface that
     *               the method was declared in, which may be a superinterface of the
     *               proxy interface that the proxy class inherits the method through.
     * @param args   an array of objects containing the values of the
     *               arguments passed in the method invocation on the proxy instance,
     *               or {@code null} if interface method takes no arguments.
     *               Arguments of primitive types are wrapped in instances of the
     *               appropriate primitive wrapper class, such as
     *               {@code java.lang.Integer} or {@code java.lang.Boolean}.
     * @return the value to return from the method invocation on the
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
     * @throws Throwable the exception to throw from the method
     *                   invocation on the proxy instance.  The exception's type must be
     *                   assignable either to any of the exception types declared in the
     *                   {@code throws} clause of the interface method or to the
     *                   unchecked exception types {@code java.lang.RuntimeException}
     *                   or {@code java.lang.Error}.  If a checked exception is
     *                   thrown by this method that is not assignable to any of the
     *                   exception types declared in the {@code throws} clause of
     *                   the interface method, then an
     *                   {@link UndeclaredThrowableException} containing the
     *                   exception that was thrown by this method will be thrown by the
     *                   method invocation on the proxy instance.
     * @see UndeclaredThrowableException
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method);
        return null;
    }

    public static void main(String[] args) {

        /**
         * 保存JVM自动生成的代理类的字节码文件
         */
        SaveProxyByteCodeUtil.saveProxyByteCode();

        /**
         * 构建调用器实例
         */
        MyInvocationHandlerExample handler = new MyInvocationHandlerExample();

        /**
         * 通过JDK的代理类，获取实现我们定义的接口的动态代理类的实例，这是核心，这个动态代理类是自动生成的
         */
        MyInterface o = (MyInterface) Proxy.newProxyInstance(
                MyInvocationHandlerExample.class.getClassLoader(),
                new Class[]{MyInterface.class}, handler);

        /**
         * o 的本质是一个JVM自动生成的动态代理类的实例
         */
        o.doSomething();
    }
}

/**
 *

 从JVM自动生成的字节码，我可以看到
 1：自动生成的代理是一个公共的常量类
 2：这个自动生产的代理类的命名方式比较特别 $Proxy0 $表示是JVM自动生成的；Proxy表示这是一个动态代理类；0表示这是JVM自动生成的第一个动态代理类
 3：这个自动生成的代理诶继承了Proxy代理类
 4：这个自动生成的代理类实现了MyInterface接口，这个接口时我们自己定义的接口
 5：这个自动生成的代理类重写了 java.lang.Object 的 equals()/hashCode()/toString()等方法，然后，实现了我们在接口中声明的方法doSomething()方法
 6：这个自动生成的代理类有一个有参的构造方法，底层实现调用了父类的构造方法
 7：在这个自动生成的代理类中，通过一个静态代码块利用JAVA反射获取对应的方法，这些方法是写死的，就是在代理类中重写和实现的那四个方法
 8：由此可见，动态代理的本质=代理模式+JVM动态生成代理类的字节码，动态的关键就在于代理类的字节码文件时动态生成的而已

 */

/*

package com.sun.proxy;

import com.godtrue.DynamicallyProxies.DynamicallyImplementAnInterface.MyInterface;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

public final class $Proxy0 extends Proxy implements MyInterface {
    private static Method m1;
    private static Method m3;
    private static Method m2;
    private static Method m0;

    public $Proxy0(InvocationHandler var1) throws  {
        super(var1);
    }

    public final boolean equals(Object var1) throws  {
        try {
            return ((Boolean)super.h.invoke(this, m1, new Object[]{var1})).booleanValue();
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final void doSomething() throws  {
        try {
            super.h.invoke(this, m3, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final String toString() throws  {
        try {
            return (String)super.h.invoke(this, m2, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final int hashCode() throws  {
        try {
            return ((Integer)super.h.invoke(this, m0, (Object[])null)).intValue();
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    static {
        try {
            m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
            m3 = Class.forName("com.godtrue.DynamicallyProxies.DynamicallyImplementAnInterface.MyInterface").getMethod("doSomething");
            m2 = Class.forName("java.lang.Object").getMethod("toString");
            m0 = Class.forName("java.lang.Object").getMethod("hashCode");
        } catch (NoSuchMethodException var2) {
            throw new NoSuchMethodError(var2.getMessage());
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }
}


*/
