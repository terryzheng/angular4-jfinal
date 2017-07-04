package com.demo.jfinal.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * 创建时间：2017年6月19日 下午4:37:38 项目名称：angular4-jfinal
 * 
 * @author terry.zheng
 * @version 1.0
 * @since JDK 1.7.0_75 文件名称：Student.java 类说明：
 */
public class Student extends Model<Student> {
	/** serialVersionUID */
	private static final long serialVersionUID = -3122831011449151182L;
	
	public static final Student dao = new Student().dao();
}
