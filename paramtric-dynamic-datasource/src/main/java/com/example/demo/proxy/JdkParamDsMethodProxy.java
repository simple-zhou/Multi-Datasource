package com.example.demo.proxy;

import com.example.demo.context.DynamicDataSourceContextHolder;
import com.example.demo.util.DataSourceUtil;
import com.example.demo.vo.DbInfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkParamDsMethodProxy implements InvocationHandler {

    private String dataSourceKey;
    private DbInfo dbInfo;
    private Object targetObject;

    public JdkParamDsMethodProxy(String dataSourceKey, DbInfo dbInfo, Object targetObject) {
        this.dataSourceKey = dataSourceKey;
        this.dbInfo = dbInfo;
        this.targetObject = targetObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 切换数据源
        DataSourceUtil.addDataSourceToDynamic(dataSourceKey, dbInfo);
        DynamicDataSourceContextHolder.setContextKey(dataSourceKey);

        // 调用方法
        Object result = method.invoke(targetObject, args);
        DynamicDataSourceContextHolder.removeContextKey();
        // 切换数据源
        return result;
    }

    /**
     * 创建代理
     */
    public static Object createProxyInstance(Object targetObject, String dataSourceKey, DbInfo dbInfo) throws Exception {
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader()
                , targetObject.getClass().getInterfaces(), new JdkParamDsMethodProxy(dataSourceKey, dbInfo, targetObject));
    }
}
