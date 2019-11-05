package com.godtrue.StaticProxies.ByParentClass;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-11-04
 */
public class StaticProxy extends MyParentClass {
    MyParentClass myInterface = new Target();

    @Override
    public void doSomething() {
        myInterface.doSomething();
    }
}
