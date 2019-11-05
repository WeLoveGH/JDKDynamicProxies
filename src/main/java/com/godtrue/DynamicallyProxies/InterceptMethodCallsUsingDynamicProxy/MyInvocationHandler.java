package com.godtrue.DynamicallyProxies.InterceptMethodCallsUsingDynamicProxy;

import com.godtrue.DynamicallyProxies.SaveProxyByteCodeUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-11-04
 */
public class MyInvocationHandler implements InvocationHandler {

    private String theString;

    public MyInvocationHandler (String theString) {
        this.theString = theString;
    }

    public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before method call : " + method.getName());
        Object result = method.invoke(theString, args);
        System.out.println("after method call : " + method.getName());
        return result;
    }

    public static void main (String[] args) {

        /**
         * 保存JVM动态生成的代理类字节码文件
         */
        SaveProxyByteCodeUtil.saveProxyByteCode();

        /**
         * 构建调用处理器实例
         */
        MyInvocationHandler handler = new MyInvocationHandler("the example string");

        /**
         * 获取动态代理的实例，注意：指定了他的类加载器，需要实现的接口列表、和实现其功能增强的调用处理器
         */
        CharSequence o = (CharSequence) Proxy.newProxyInstance(
                MyInvocationHandler.class.getClassLoader(),
                new Class[]{CharSequence.class}, handler);

        System.out.println(o.length());

        System.out.println(o.subSequence(4, 8));
    }
}


/*

package com.sun.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.stream.IntStream;

public final class $Proxy0 extends Proxy implements CharSequence {
    private static Method m1;
    private static Method m3;
    private static Method m5;
    private static Method m6;
    private static Method m2;
    private static Method m4;
    private static Method m7;
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

    public final char charAt(int var1) throws  {
        try {
            return ((Character)super.h.invoke(this, m3, new Object[]{var1})).charValue();
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final CharSequence subSequence(int var1, int var2) throws  {
        try {
            return (CharSequence)super.h.invoke(this, m5, new Object[]{var1, var2});
        } catch (RuntimeException | Error var4) {
            throw var4;
        } catch (Throwable var5) {
            throw new UndeclaredThrowableException(var5);
        }
    }

    public final IntStream chars() throws  {
        try {
            return (IntStream)super.h.invoke(this, m6, (Object[])null);
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

    public final int length() throws  {
        try {
            return ((Integer)super.h.invoke(this, m4, (Object[])null)).intValue();
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final IntStream codePoints() throws  {
        try {
            return (IntStream)super.h.invoke(this, m7, (Object[])null);
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
            m3 = Class.forName("java.lang.CharSequence").getMethod("charAt", Integer.TYPE);
            m5 = Class.forName("java.lang.CharSequence").getMethod("subSequence", Integer.TYPE, Integer.TYPE);
            m6 = Class.forName("java.lang.CharSequence").getMethod("chars");
            m2 = Class.forName("java.lang.Object").getMethod("toString");
            m4 = Class.forName("java.lang.CharSequence").getMethod("length");
            m7 = Class.forName("java.lang.CharSequence").getMethod("codePoints");
            m0 = Class.forName("java.lang.Object").getMethod("hashCode");
        } catch (NoSuchMethodException var2) {
            throw new NoSuchMethodError(var2.getMessage());
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }
}

 */
