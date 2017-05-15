package com.demo.jfinal;

import com.demo.jfinal.controller.HelloController;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PropKit;
import com.jfinal.template.Engine;

/**
 * 创建时间：2017年5月10日 下午3:41:19 项目名称：angular4-jfinal
 * 
 * @author terry.zheng
 * @version 1.0
 * @since JDK 1.7.0_75 文件名称：ApplicationConfig.java 类说明：
 */
public class ApplicationConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants c) {
		PropKit.use("config.txt");
		c.setDevMode(true);
	}

	@Override
	public void configRoute(Routes r) {
		r.setBaseViewPath("/views");
		// r.addInterceptor(new FrontInterceptor());
		r.add("/hello", HelloController.class);
	}

	@Override
	public void configEngine(Engine e) {
		//e.addSharedFunction("/_view/common/_layout.html");
	}

	@Override
	public void configPlugin(Plugins p) {

	}

	@Override
	public void configInterceptor(Interceptors i) {
		//i.add(globalActionInterceptor);
	}

	@Override
	public void configHandler(Handlers h) {
		//h.add(new ResourceHandler());
	}

	public static void main(String[] args) {
		JFinal.start("WebRoot", 8080, "/", 30);
	}
}
