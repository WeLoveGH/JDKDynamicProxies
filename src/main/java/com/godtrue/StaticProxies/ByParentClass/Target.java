package com.godtrue.StaticProxies.ByParentClass;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-11-04
 */
public class Target extends MyParentClass {
    @Override
    public void doSomething() {
        System.out.println(" i am Target");
    }
}
