package org.example.invocationA;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AgentHandler implements InvocationHandler {

    private Star person;

    public AgentHandler(Star person) {
        this.person = person;
    }

    // java.lang.reflect.InvocationHandler && java.lang.reflect.Proxy类
    // 这个方法 的proxy是代理人本身把 方法和参数
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("11111");
        System.out.println(proxy);
        Object result = method.invoke(person, args);
        System.out.println("222222");
        return result;
    }

    // 对外工作室 = 明星, 真正的杂事儿是经纪人在处理 经纪人(明星) 经纪人要有权利处理事情 必须得到官方授权InvocationHandle
    // 一共有三个类 明星本星star 工作室proxyInstance 和经纪人agentHandler(主要的处理逻辑) 类型为InvocationHandler的stuProxy
    public static void main(String[] args) {
        Star star = new Jay("Bob");
        InvocationHandler agentHandler = new AgentHandler(star);
        // 因为是jdk动态代理 所以需要interfaces为啥还是list呢 多接口 本来就可以根据接口去代理目标类下面的方法
        // classLoader用默认的会咋样
        Star proxyInstance = (Star) Proxy.newProxyInstance(Star.class.getClassLoader(), new Class<?>[]{Star.class}, agentHandler);
//        Star proxyInstance2 = (Star) Proxy.newProxyInstance(Star.class.getClassLoader(), star.getClass().getInterfaces(), agentHandler);
        proxyInstance.sing();
        proxyInstance.dance();
//        proxyInstance.rap(); 爆红
    }

}
