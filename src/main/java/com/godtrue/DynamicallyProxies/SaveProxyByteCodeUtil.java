package com.godtrue.DynamicallyProxies;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-11-05
 */
public class SaveProxyByteCodeUtil {
    public static final void saveProxyByteCode(){
        /**
         * 将代理类的的字节码文件保存下来的设置，非常的有用
         */
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
    }
}
