package com.demo.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * 创建时间：2017年5月26日 下午3:49:04 项目名称：angular4-jfinal
 * 
 * @author terry.zheng
 * @version 1.0
 * @since JDK 1.7.0_75 文件名称：DemoInterceptor.java 类说明：
 */
public class Demo1Interceptor implements Interceptor {
	public void intercept(Invocation inv) {
		System.out.println("before1");
		inv.invoke();
		System.out.println("after1");
	}
}
