package com.godtrue.DynamicallyProxies.DynamicProxiesImplementingDecorators;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-11-04
 */
public class MyObject implements IObject {

    public String getData() {
        return "expensiveData-" + System.nanoTime();
    }
}
