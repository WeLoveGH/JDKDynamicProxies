package com.godtrue.cglib;

import com.godtrue.DynamicallyProxies.SaveProxyByteCodeUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-11-06
 */
public class JDKDynamicallyProxySample {
    public static void main(String[] args) {

        /**
         * 保存JVM动态生成的代理类字节码文件
         */
        SaveProxyByteCodeUtil.saveProxyByteCode();

        /**
         * 构建一个列表对象，并且加入若干元素
         */
        List<String> ary = new ArrayList<String>();
        ary.add("Hello");
        ary.add("Proxy");
        ary.add("World!!");

        /**
         * 通过JDK的代理类获取动态代理类的实例，这块是核心，proxyAry 这个已经是 list 的代理类了，是JVM自动生成的，且直接生产了字节码文件，没有源代码，这也是称为动态的原因
         */
        @SuppressWarnings("unchecked")
        List<String> proxyAry = (List<String>) Proxy.newProxyInstance(JDKDynamicallyProxySample.class.getClassLoader(), new Class<?>[]{List.class}, new MyInvocationHandler(ary));

        /**
         * 循环遍历且打印列表的内容，注意：放入三个元素，如果不使用代理来增强其功能，获取第四个元素时是会直接报错的，这里没有报错，直接打印了一个 Bow!!
         */
        for (int i = 0; i < 4; i++) {
            log(proxyAry.get(i));
        }
    }

    static class MyInvocationHandler implements InvocationHandler {

        private List<String> ary;

        public MyInvocationHandler(List<String> ary) {
            this.ary = ary;
        }

        /**
         * 调用处理器增强的方法，这块也是核心，代理类的功能增强就在这里
         * @param proxy
         * @param method
         * @param args
         * @return
         * @throws Throwable
         */
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (isFouthGet(method, args)) {
                return "Bow!!";
            }
            return method.invoke(ary, args);
        }

        private boolean isFouthGet(Method method, Object[] args) {
            return "get".equals(method.getName()) && ((Integer)args[0]) == 3;
        }
    }

    private static void log(Object msg) {
        System.out.println(msg);
    }
}

/**
 *
 * 如下是JVM生成的动态代理类的字节码的反编译结果，可以看出：
 *
 1：自动生成的代理类是一个公共的常量类
 2：这个自动生产的代理类的命名方式比较特别 $Proxy0 $表示是JVM自动生成的；Proxy表示这是一个动态代理类；0表示这是JVM自动生成的第一个动态代理类
 3：这个自动生成的代理类继承了Proxy代理类
 4：这个自动生成的代理类实现了List接口
 5：这个自动生成的代理类重写了 java.lang.Object 的 equals()/hashCode()/toString()等方法，然后，实现了我们在接口中声明的方法其他方法
 6：这个自动生成的代理类有一个有参的构造方法，底层实现调用了父类的构造方法
 7：在这个自动生成的代理类中，通过一个静态代码块利用JAVA反射获取对应的方法，这些方法是写死的，就是在代理类中重写和实现的那四个方法
 8：由此可见，动态代理的本质=代理模式+JVM动态生成代理类的字节码，动态的关键就在于代理类的字节码文件时动态生成的而已
 9：代理类是必须存在的，只是可以选择是自己写，还是通过编码自动生成，显然自动生成的代码更优雅一些

 */

/*

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public final class $Proxy0 extends Proxy implements List {
    private static Method m1;
    private static Method m23;
    private static Method m6;
    private static Method m26;
    private static Method m25;
    private static Method m17;
    private static Method m5;
    private static Method m10;
    private static Method m18;
    private static Method m0;
    private static Method m32;
    private static Method m7;
    private static Method m30;
    private static Method m31;
    private static Method m28;
    private static Method m24;
    private static Method m9;
    private static Method m4;
    private static Method m21;
    private static Method m11;
    private static Method m20;
    private static Method m16;
    private static Method m2;
    private static Method m13;
    private static Method m8;
    private static Method m14;
    private static Method m27;
    private static Method m12;
    private static Method m19;
    private static Method m22;
    private static Method m3;
    private static Method m29;
    private static Method m15;

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

    public final boolean containsAll(Collection var1) throws  {
        try {
            return ((Boolean)super.h.invoke(this, m23, new Object[]{var1})).booleanValue();
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final boolean remove(Object var1) throws  {
        try {
            return ((Boolean)super.h.invoke(this, m6, new Object[]{var1})).booleanValue();
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final boolean removeAll(Collection var1) throws  {
        try {
            return ((Boolean)super.h.invoke(this, m26, new Object[]{var1})).booleanValue();
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final ListIterator listIterator() throws  {
        try {
            return (ListIterator)super.h.invoke(this, m25, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final Object[] toArray() throws  {
        try {
            return (Object[])super.h.invoke(this, m17, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final Object remove(int var1) throws  {
        try {
            return (Object)super.h.invoke(this, m5, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final boolean contains(Object var1) throws  {
        try {
            return ((Boolean)super.h.invoke(this, m10, new Object[]{var1})).booleanValue();
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final Object[] toArray(Object[] var1) throws  {
        try {
            return (Object[])super.h.invoke(this, m18, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
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

    public final void forEach(Consumer var1) throws  {
        try {
            super.h.invoke(this, m32, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final Object get(int var1) throws  {
        try {
            return (Object)super.h.invoke(this, m7, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final boolean removeIf(Predicate var1) throws  {
        try {
            return ((Boolean)super.h.invoke(this, m30, new Object[]{var1})).booleanValue();
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final Stream parallelStream() throws  {
        try {
            return (Stream)super.h.invoke(this, m31, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final void sort(Comparator var1) throws  {
        try {
            super.h.invoke(this, m28, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final ListIterator listIterator(int var1) throws  {
        try {
            return (ListIterator)super.h.invoke(this, m24, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final void clear() throws  {
        try {
            super.h.invoke(this, m9, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final void add(int var1, Object var2) throws  {
        try {
            super.h.invoke(this, m4, new Object[]{var1, var2});
        } catch (RuntimeException | Error var4) {
            throw var4;
        } catch (Throwable var5) {
            throw new UndeclaredThrowableException(var5);
        }
    }

    public final boolean addAll(int var1, Collection var2) throws  {
        try {
            return ((Boolean)super.h.invoke(this, m21, new Object[]{var1, var2})).booleanValue();
        } catch (RuntimeException | Error var4) {
            throw var4;
        } catch (Throwable var5) {
            throw new UndeclaredThrowableException(var5);
        }
    }

    public final boolean isEmpty() throws  {
        try {
            return ((Boolean)super.h.invoke(this, m11, (Object[])null)).booleanValue();
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final boolean addAll(Collection var1) throws  {
        try {
            return ((Boolean)super.h.invoke(this, m20, new Object[]{var1})).booleanValue();
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final List subList(int var1, int var2) throws  {
        try {
            return (List)super.h.invoke(this, m16, new Object[]{var1, var2});
        } catch (RuntimeException | Error var4) {
            throw var4;
        } catch (Throwable var5) {
            throw new UndeclaredThrowableException(var5);
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

    public final int lastIndexOf(Object var1) throws  {
        try {
            return ((Integer)super.h.invoke(this, m13, new Object[]{var1})).intValue();
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final int indexOf(Object var1) throws  {
        try {
            return ((Integer)super.h.invoke(this, m8, new Object[]{var1})).intValue();
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final void replaceAll(UnaryOperator var1) throws  {
        try {
            super.h.invoke(this, m14, new Object[]{var1});
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final boolean retainAll(Collection var1) throws  {
        try {
            return ((Boolean)super.h.invoke(this, m27, new Object[]{var1})).booleanValue();
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final Iterator iterator() throws  {
        try {
            return (Iterator)super.h.invoke(this, m12, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final Spliterator spliterator() throws  {
        try {
            return (Spliterator)super.h.invoke(this, m19, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final Object set(int var1, Object var2) throws  {
        try {
            return (Object)super.h.invoke(this, m22, new Object[]{var1, var2});
        } catch (RuntimeException | Error var4) {
            throw var4;
        } catch (Throwable var5) {
            throw new UndeclaredThrowableException(var5);
        }
    }

    public final boolean add(Object var1) throws  {
        try {
            return ((Boolean)super.h.invoke(this, m3, new Object[]{var1})).booleanValue();
        } catch (RuntimeException | Error var3) {
            throw var3;
        } catch (Throwable var4) {
            throw new UndeclaredThrowableException(var4);
        }
    }

    public final Stream stream() throws  {
        try {
            return (Stream)super.h.invoke(this, m29, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final int size() throws  {
        try {
            return ((Integer)super.h.invoke(this, m15, (Object[])null)).intValue();
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    static {
        try {
            m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
            m23 = Class.forName("java.util.List").getMethod("containsAll", Class.forName("java.util.Collection"));
            m6 = Class.forName("java.util.List").getMethod("remove", Class.forName("java.lang.Object"));
            m26 = Class.forName("java.util.List").getMethod("removeAll", Class.forName("java.util.Collection"));
            m25 = Class.forName("java.util.List").getMethod("listIterator");
            m17 = Class.forName("java.util.List").getMethod("toArray");
            m5 = Class.forName("java.util.List").getMethod("remove", Integer.TYPE);
            m10 = Class.forName("java.util.List").getMethod("contains", Class.forName("java.lang.Object"));
            m18 = Class.forName("java.util.List").getMethod("toArray", Class.forName("[Ljava.lang.Object;"));
            m0 = Class.forName("java.lang.Object").getMethod("hashCode");
            m32 = Class.forName("java.util.List").getMethod("forEach", Class.forName("java.util.function.Consumer"));
            m7 = Class.forName("java.util.List").getMethod("get", Integer.TYPE);
            m30 = Class.forName("java.util.List").getMethod("removeIf", Class.forName("java.util.function.Predicate"));
            m31 = Class.forName("java.util.List").getMethod("parallelStream");
            m28 = Class.forName("java.util.List").getMethod("sort", Class.forName("java.util.Comparator"));
            m24 = Class.forName("java.util.List").getMethod("listIterator", Integer.TYPE);
            m9 = Class.forName("java.util.List").getMethod("clear");
            m4 = Class.forName("java.util.List").getMethod("add", Integer.TYPE, Class.forName("java.lang.Object"));
            m21 = Class.forName("java.util.List").getMethod("addAll", Integer.TYPE, Class.forName("java.util.Collection"));
            m11 = Class.forName("java.util.List").getMethod("isEmpty");
            m20 = Class.forName("java.util.List").getMethod("addAll", Class.forName("java.util.Collection"));
            m16 = Class.forName("java.util.List").getMethod("subList", Integer.TYPE, Integer.TYPE);
            m2 = Class.forName("java.lang.Object").getMethod("toString");
            m13 = Class.forName("java.util.List").getMethod("lastIndexOf", Class.forName("java.lang.Object"));
            m8 = Class.forName("java.util.List").getMethod("indexOf", Class.forName("java.lang.Object"));
            m14 = Class.forName("java.util.List").getMethod("replaceAll", Class.forName("java.util.function.UnaryOperator"));
            m27 = Class.forName("java.util.List").getMethod("retainAll", Class.forName("java.util.Collection"));
            m12 = Class.forName("java.util.List").getMethod("iterator");
            m19 = Class.forName("java.util.List").getMethod("spliterator");
            m22 = Class.forName("java.util.List").getMethod("set", Integer.TYPE, Class.forName("java.lang.Object"));
            m3 = Class.forName("java.util.List").getMethod("add", Class.forName("java.lang.Object"));
            m29 = Class.forName("java.util.List").getMethod("stream");
            m15 = Class.forName("java.util.List").getMethod("size");
        } catch (NoSuchMethodException var2) {
            throw new NoSuchMethodError(var2.getMessage());
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }
}


 */