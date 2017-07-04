package com.demo.jfinal.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import com.demo.jfinal.interceptor.Demo1Interceptor;
import com.demo.jfinal.interceptor.DemoInterceptor;
import com.demo.jfinal.model.Blog;
import com.demo.jfinal.model.Student;
import com.demo.jfinal.service.DemoService;
import com.demo.jfinal.service.impl.DemoServiceImpl;
import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Enhancer;
import com.jfinal.core.Controller;
import com.jfinal.kit.Kv;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;

/**
 * 创建时间：2017年5月10日 下午3:48:31 项目名称：angular4-jfinal
 * 
 * @author terry.zheng
 * @version 1.0
 * @since JDK 1.7.0_75 文件名称：HelloController.java 类说明：
 */
@Before(DemoInterceptor.class)
public class HelloController extends Controller {
	DemoService service = Enhancer.enhance(DemoServiceImpl.class, Demo1Interceptor.class);

	// @ActionKey("/user/login")
	public void index() {
		Student s = Student.dao.findById(2);
		renderText("Hello JFinal World:" + s.getStr("name"));
		System.out.println(getParaToInt(0));
		System.out.println(PropKit.get("hello"));
		Boolean succeed = Db.tx(new IAtom() {
			public boolean run() throws SQLException {
				int count1 = Db.update("update student set name=? where id =?", "aaaa", 1);
				int count2 = Db.update("update student set age=age + ? where id =?", 1, 2);
				return count1 == 1 && count2 == 1;
			}
		});
		System.out.println(succeed);
	}

	@Before(DemoInterceptor.class)
	public void test() {
		Map<String, String[]> maps = getParaMap();
		Enumeration<String> map = getParaNames();
		while (map.hasMoreElements()) {
			String a = map.nextElement();
			System.out.println(a);
			System.out.println(maps.get(a)[0]);
		}
		Student s = Student.dao.findFirst("select * from student where age < 23");
		s.set("name", "aaaa").set("age", s.getStr("name").length()).update();
		renderText("Test" + getParaToDate("time"));
	}

	public void save() {
		Blog blog = getBean(Blog.class, "");
		new Student().set("name", blog.getTitle()).set("age", blog.getContent().length()).save();
		setAttr("info", "content");
		setSessionAttr("s", blog);
		renderText("Blog:" + blog.getTitle());
	}

	@Clear
	@Before(DemoInterceptor.class)
	public void download() {
		renderJson();

		setAttr("data", getSessionAttr("s"));

		setAttr("s", getSessionAttr("s"));

		service.demo();
		// renderNull();
		// render(new JsonRender().forIE());
		// renderJson("ret", getSessionAttr("s"));
		// renderFile("a.jpg");
	}

	public void low() {
		List sl = Arrays.asList(1, 2, 3, 4);
		SqlPara sp = Db.getSqlPara("selectStudents", Kv.by("idList", sl));

		List<Record> s = Db.find(sp.getSql());

		SqlPara sqlPara = Db.getSqlPara("score.findLowestScore", 60, 5, 1);
		List<Record> list = Db.find(sqlPara);

		for (Record r : list) {
			System.out.println(r.getStr("suject"));
		}

		setAttr("s", s);
		setAttr("data", list);
		renderJson();
	}
	
	public void multi(){
		Student s1 = Student.dao.findById(1);
		Student s2 = Student.dao.use("main").findById(1);
		renderText(s1.getStr("name"));
	}
}
