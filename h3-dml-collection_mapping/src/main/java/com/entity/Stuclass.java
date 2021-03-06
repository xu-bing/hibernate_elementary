package com.entity;

// Generated 2018-5-15 15:31:35 by Hibernate Tools 3.4.0.CR1

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import java.util.Set;

/**
 * Stuclass generated by hbm2java
 */
public class Stuclass implements java.io.Serializable {

	private Integer cid;
	private String cname;
	
	// 表示1个班级下有多个学生
	private Set studentSet = new HashSet();
	
	private List studentList = new ArrayList();
	
	private Map studentMap = new HashMap();
	
	private List studentBag = new ArrayList();

	public List getStudentList() {
		return studentList;
	}

	public void setStudentList(List studentList) {
		this.studentList = studentList;
	}

	public Set getStudentSet() {
		return studentSet;
	}

	public void setStudentSet(Set studentSet) {
		this.studentSet = studentSet;
	}

	public Stuclass() {
	}

	public Stuclass(String cname) {
		this.cname = cname;
	}

	public Integer getCid() {
		return this.cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Map getStudentMap() {
		return studentMap;
	}

	public void setStudentMap(Map studentMap) {
		this.studentMap = studentMap;
	}

	public List getStudentBag() {
		return studentBag;
	}

	public void setStudentBag(List studentBag) {
		this.studentBag = studentBag;
	}

}
