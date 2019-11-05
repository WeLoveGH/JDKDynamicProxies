package com.godtrue.StaticProxies.ByInterface;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-11-04
 */
public class StaticProxy implements MyInterface{
    MyInterface myInterface = new Target();

    public void doSomething() {
        myInterface.doSomething();
    }
}
