package com.demo.jfinal.service.impl;

import com.demo.jfinal.model.Student;
import com.demo.jfinal.service.DemoService;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.tx.Tx;

/**
 * 创建时间：2017年5月31日 下午4:56:55 项目名称：angular4-jfinal
 * 
 * @author terry.zheng
 * @version 1.0
 * @since JDK 1.7.0_75 文件名称：DemoServiceImpl.java 类说明：
 */
public class DemoServiceImpl implements DemoService {
	@Before(Tx.class)
	public void demo() {
		Page<Student> students = Student.dao.paginate(3, 2, "select *", "from student");
		for(Student s:students.getList()){
			System.out.println(s.getStr("name"));
		}
	}
}
