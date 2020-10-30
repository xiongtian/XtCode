package com.study.spring5.demo4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author xiongtian
 * @create 2020/9/5-16:06
 */
public class JDKProxy {
    public static void main(String[] args) {
        // 创建接口实现类的代理对象
        Class[] interfaces = {UserDao.class};
        // 使用匿名内部类的方式
      /*  Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfaces, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        })*/
      UserDao userDao=new UserDaoImpl();
        UserDao dao = (UserDao) Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfaces, new UserDaoProxy(userDao));
        int result = dao.add(1, 2);
        System.out.println(result);

    /*
    输出内容：
        方法之前执行add:传递的参数。。。[1, 2]
        方法之后执行com.study.spring5.demo4.UserDaoImpl@6ff3c5b5
        3
    */
    }
}
// 创建代理对象的代码
class UserDaoProxy implements InvocationHandler {

    // 有参构造进行传递
    private Object obj;

    public UserDaoProxy(Object obj) {
        this.obj=obj;
    }

    //增强的逻辑
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 1、创建代理对象
        //方法之前进行处理
        System.out.println("方法之前执行"+method.getName()+":传递的参数。。。"+ Arrays.toString(args));
        Object res = method.invoke(obj, args);
        // 方法之后进行处理
        System.out.println("方法之后执行"+obj);

        return res;
    }
}