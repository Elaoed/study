package org.example.cglibInvocation;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class AgentJ implements MethodInterceptor {

    public static void main(String[] args) {
        // 明星
        JJ star = new JJ();
        // 经纪人培训公司
        Enhancer enhancer = new Enhancer();
        // 为下面生成的代理费指定父类
        enhancer.setSuperclass(star.getClass());

        enhancer.setCallback(new AgentJ());
        // 工作室 TFBoys二代
        JJ proxyInstance = (JJ) enhancer.create();
        proxyInstance.sing();;
        proxyInstance.dance();;
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("intercepted!!!!!!!");
        Object o1 = methodProxy.invokeSuper(o, objects);
        return o1;
    }
}
