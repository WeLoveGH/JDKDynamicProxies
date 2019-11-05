package com.godtrue.ShowSystemProperties;

import java.util.Enumeration;
import java.util.Properties;

/**
 * @description：
 * @author：qianyingjie1
 * @create：2019-11-05
 */
public class DisplayApp {
    public static void main(String[] args) {
        Properties systemProperties = System.getProperties();
        Enumeration enuProp = systemProperties.propertyNames();
        while (enuProp.hasMoreElements()) {
            String propertyName = (String) enuProp.nextElement();
            String propertyValue = systemProperties.getProperty(propertyName);
            System.out.println(propertyName + ": " + propertyValue);
        }
    }
}
