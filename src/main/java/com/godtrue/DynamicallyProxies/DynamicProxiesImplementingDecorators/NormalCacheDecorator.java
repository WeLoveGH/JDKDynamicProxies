package com.godtrue.DynamicallyProxies.DynamicProxiesImplementingDecorators;

import com.godtrue.DynamicallyProxies.SaveProxyByteCodeUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-11-04
 */
public class NormalCacheDecorator implements IObject {

    private IObject original;

    private Map<String, Object> cacheData = new HashMap<String,Object>();

    public NormalCacheDecorator(IObject original) {
        this.original = original;
    }

    public String getData() {
        Object data = cacheData.get("getData");
        if (data == null) {
            data = original.getData();
            cacheData.put("getData", data);

        }
        return (String) data;
    }

    public static void main(String[] args) {

        /**
         * 保存JVM动态生成的代理类字节码文件
         */
        SaveProxyByteCodeUtil.saveProxyByteCode();

        MyObject object = new MyObject();

        IObject decorator = new NormalCacheDecorator(object);

        System.out.println(decorator.getData());
        System.out.println(decorator.getData());
    }
}
