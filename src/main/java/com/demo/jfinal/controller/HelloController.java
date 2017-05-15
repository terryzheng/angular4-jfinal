package com.demo.jfinal.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.PropKit;

/**
 * 创建时间：2017年5月10日 下午3:48:31 项目名称：angular4-jfinal
 * 
 * @author terry.zheng
 * @version 1.0
 * @since JDK 1.7.0_75 文件名称：HelloController.java 类说明：
 */
public class HelloController extends Controller {
	//@ActionKey("/user/login")
	public void index() {
		renderText("Hello JFinal World");
		System.out.println(getPara(0));
		System.out.println(PropKit.get("hello"));
	}
}
