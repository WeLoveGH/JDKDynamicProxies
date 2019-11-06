package com.godtrue.cglib;

import com.godtrue.DynamicallyProxies.SaveProxyByteCodeUtil;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-11-06
 */
public class CGLibDynamicallyProxySample {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        /**
         * 保存JVM动态生成的代理类字节码文件，注意：这个配置对于通过 CGLib 动态生成代理类已经不灵验了
         */
        SaveProxyByteCodeUtil.saveProxyByteCode();

        /**
         * 构建一个列表对象，并且加入若干元素
         */
        List<String> ary = new ArrayList<String>();
        ary.add("Hello");
        ary.add("Proxy");
        ary.add("World!!");

        log("create a interface proxy");

        /**
         * 通过 CGLib 三方库来生成动态的代理类，本地也是通过反射来生动动态代理类的实例的，通过接口来生产成动态代理类
         */
        List<String> proxyAry = (List<String>) Enhancer.create(List.class, new MyInvocationHandler(ary));

        System.out.println(proxyAry.getClass().getName());

        /**
         * 循环遍历且打印列表的内容，注意：放入三个元素，如果不使用代理来增强其功能，获取第四个元素时是会直接报错的，这里没有报错，直接打印了一个 Bow!!
         */
        for (int i = 0; i < 4; i++) { log(proxyAry.get(i)); }

        System.out.println("\n\n\n");

        log("create a class proxy");
        /**
         * 通过 CGLib 三方库来生成动态的代理类，本地也是通过反射来生动动态代理类的实例的，通过父类来生产成动态代理类
         */
        proxyAry = (List<String>)Enhancer.create(ArrayList.class, new MyInvocationHandler(ary));
        System.out.println(proxyAry.getClass().getName());

        /**
         * 循环遍历且打印列表的内容，注意：放入三个元素，如果不使用代理来增强其功能，获取第四个元素时是会直接报错的，这里没有报错，直接打印了一个 Bow!!
         */
        for (int i = 0; i < 4; i++) { log(proxyAry.get(i)); }
    }

    static class MyInvocationHandler implements MethodInterceptor {

        private List<String> ary;

        public MyInvocationHandler(List<String> ary) {
            this.ary = ary;
        }

        /**
         * 调用处理器增强的方法，这块也是核心，代理类的功能增强就在这里
         *
         * @param obj "this", the enhanced object
         * @param method intercepted Method
         * @param args argument array; primitive types are wrapped
         * @param proxy used to invoke super (non-intercepted method); may be called
         * as many times as needed
         */
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            if (isFouthGet(method, args)) {
                return "Bow!!";
            }
            return proxy.invoke(ary, args);
        }

        private boolean isFouthGet(Method method, Object[] args) {
            return "get".equals(method.getName()) && ((Integer)args[0]) == 3;
        }
    }

    private static void log(Object msg) {
        System.out.println(msg);
    }
}


/*

通过日志可以看到：

1：CGLib 可用通过实现接口和继承父类的方式实现动态代理
2：动态的代理的本质没变 动态代理 = 代理模式 + 动态生成代理类的字节码实例（通过反射动态生成代理类的字节码文件，然后通过这个动态代理类的字节码文件生成动态代理的实例）

create a interface proxy
$java.util.List$$EnhancerByCGLIB$$3ede5e5
Hello
Proxy
World!!
Bow!!




create a class proxy
$java.util.ArrayList$$EnhancerByCGLIB$$b347bdfe
Hello
Proxy
World!!
Bow!!

Process finished with exit code 0


*/