package com.demo.jfinal;

import com.demo.jfinal.controller.HelloController;
import com.demo.jfinal.model.Student;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.PostgreSqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
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
		PropKit.use("config/config.properties");
		c.setDevMode(true);
	}

	@Override
	public void configRoute(Routes r) {
		r.setBaseViewPath("/views");
		// r.addInterceptor(new DemoInterceptor());
		r.add("/hello", HelloController.class);
	}

	@Override
	public void configEngine(Engine e) {
		e.addSharedFunction("/_view/common/_layout.html");
	}

	@Override
	public void configPlugin(Plugins p) {
		DruidPlugin dpPostgre = new DruidPlugin(PropKit.get("postgre.url"), PropKit.get("postgre.username"),
				PropKit.get("postgre.password"));
		p.add(dpPostgre);
		ActiveRecordPlugin arpPostgre = new ActiveRecordPlugin(dpPostgre);

		DruidPlugin dpMysql = new DruidPlugin(PropKit.get("mysql.url"), PropKit.get("mysql.username"),
				PropKit.get("mysql.password"));
		p.add(dpMysql);
		ActiveRecordPlugin arpMysql = new ActiveRecordPlugin("mysql", dpMysql);

		p.add(arpPostgre);
		arpPostgre.setDialect(new PostgreSqlDialect());
		arpPostgre.setBaseSqlTemplatePath(PathKit.getRootClassPath() + "/sql");
		arpPostgre.addSqlTemplate("all.sql");
		arpPostgre.addMapping("student", Student.class);

		p.add(arpMysql);
		arpMysql.addMapping("student", Student.class);
	}

	@Override
	public void configInterceptor(Interceptors i) {
		// i.add(globalActionInterceptor);
	}

	@Override
	public void configHandler(Handlers h) {
		// h.add(new ResourceHandler());
	}

	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 8080, "/", 30);
	}
}
