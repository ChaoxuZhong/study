package com.chaoxuzhong.study.pattern.structural.proxy;

import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;

import java.lang.reflect.Method;

public class AdditionalFeatureProxy {
    private FeaturesAdd featuresAdd;

    AdditionalFeatureProxy(FeaturesAdd featuresAdd) {
        this.featuresAdd = featuresAdd;
    }


    public Object createProxy(Object proxiedObject) {
        Class<?> clazz = proxiedObject.getClass();
        ClassLoader classLoader = clazz.getClassLoader();
        InvocationHandler invocationHandler = new FeatureAddInvocationHandler(proxiedObject);

        return Proxy.newProxyInstance(classLoader, clazz.getInterfaces(), invocationHandler);
    }

    private class FeatureAddInvocationHandler implements InvocationHandler {

        private Object proxiedObject;

        FeatureAddInvocationHandler(Object proxiedObject) {
            this.proxiedObject = proxiedObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            featuresAdd.beforeProxyMethod();
            Object object = method.invoke(proxiedObject, args);
            featuresAdd.afterProxyMethod();
            return object;
        }
    }

}
