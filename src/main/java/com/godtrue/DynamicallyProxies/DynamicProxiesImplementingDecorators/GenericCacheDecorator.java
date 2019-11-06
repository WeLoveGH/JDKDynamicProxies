package com.godtrue.DynamicallyProxies.DynamicProxiesImplementingDecorators;

import com.godtrue.DynamicallyProxies.SaveProxyByteCodeUtil;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-11-04
 */
public class GenericCacheDecorator implements InvocationHandler {

    private Map<String, Object> cachedData = new HashMap<String,Object>();

    private Object EMPTY = new Object();

    private Object obj;

    private GenericCacheDecorator(Object obj) {
        this.obj = obj;
        try {
            /**
             * 获取对象实例的详细信息
             */
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());

            /**
             * 循环遍历对象属性描述器，缓存对应的读方法信息，value的值默认为一个特殊对象引用
             */
            for (PropertyDescriptor desc : beanInfo.getPropertyDescriptors()) {
                cachedData.put(desc.getReadMethod().getName(), EMPTY);
            }

        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 装饰器方法
     * @param t
     * @param interfaceClass
     * @param <I>
     * @param <T>
     * @return
     */
    public static <I, T extends I> I decorate(T t, Class<I> interfaceClass) {
        /**
         * 构建装饰器缓存对象的实例
         */
        GenericCacheDecorator cacheableDecorator = new GenericCacheDecorator(t);

        /**
         * 动态生成代理类的实例，核心代码
         */
        return (I) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class[]{interfaceClass}, cacheableDecorator);

    }

    /**
     * 这个方法比较核心，是在调用动态
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (cachedData.containsKey(method.getName())) {
            Object o = cachedData.get(method.getName());
            if (o == EMPTY) {
                Object returned = method.invoke(obj, args);
                cachedData.put(method.getName(), returned);
                return returned;
            } else {
                return o;
            }
        }
        return method.invoke(args);
    }

    public static void main(String[] args) {

        /**
         * 保存JVM动态生成的代理类字节码文件
         */
        SaveProxyByteCodeUtil.saveProxyByteCode();

        /**
         * 构建我的对象实例
         */
        MyObject object = new MyObject();

        /**
         * 调用装饰器方法，生成对应的装饰器类的实例
         */
        IObject iObject = GenericCacheDecorator.decorate(object, IObject.class);

        /**
         * iObject 本质是一个JVM动态生成的代理类实例
         */
        System.out.println(iObject.getData());
        System.out.println(iObject.getData());
    }
}


/**
 *

 从JVM自动生成的字节码，我可以看到
 1：自动生成的代理类是一个公共的常量类
 2：这个自动生产的代理类的命名方式比较特别 $Proxy0 $表示是JVM自动生成的；Proxy表示这是一个动态代理类；0表示这是JVM自动生成的第一个动态代理类
 3：这个自动生成的代理类继承了Proxy代理类
 4：这个自动生成的代理类实现了MyInterface接口，这个接口时我们自己定义的接口
 5：这个自动生成的代理类重写了 java.lang.Object 的 equals()/hashCode()/toString()等方法，然后，实现了我们在接口中声明的方法 getData()方法
 6：这个自动生成的代理类有一个有参的构造方法，底层实现调用了父类的构造方法
 7：在这个自动生成的代理类中，通过一个静态代码块利用JAVA反射获取对应的方法，这些方法是写死的，就是在代理类中重写和实现的那四个方法
 8：由此可见，动态代理的本质=代理模式+JVM动态生成代理类的字节码，动态的关键就在于代理类的字节码文件时动态生成的而已

 */

/*

package com.sun.proxy;

import com.godtrue.DynamicallyProxies.DynamicProxiesImplementingDecorators.IObject;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

public final class $Proxy0 extends Proxy implements IObject {
    private static Method m1;
    private static Method m2;
    private static Method m3;
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

    public final String toString() throws  {
        try {
            return (String)super.h.invoke(this, m2, (Object[])null);
        } catch (RuntimeException | Error var2) {
            throw var2;
        } catch (Throwable var3) {
            throw new UndeclaredThrowableException(var3);
        }
    }

    public final String getData() throws  {
        try {
            return (String)super.h.invoke(this, m3, (Object[])null);
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
            m2 = Class.forName("java.lang.Object").getMethod("toString");
            m3 = Class.forName("com.godtrue.DynamicallyProxies.DynamicProxiesImplementingDecorators.IObject").getMethod("getData");
            m0 = Class.forName("java.lang.Object").getMethod("hashCode");
        } catch (NoSuchMethodException var2) {
            throw new NoSuchMethodError(var2.getMessage());
        } catch (ClassNotFoundException var3) {
            throw new NoClassDefFoundError(var3.getMessage());
        }
    }
}


 */