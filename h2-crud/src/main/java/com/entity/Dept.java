package com.entity;

// Generated 2018-10-4 9:38:34 by Hibernate Tools 3.4.0.CR1

/**
 * Dept generated by hbm2java
 */
public class Dept implements java.io.Serializable {

	private Integer deptno;
	private String dname;
	private String loc;

	public Dept() {
	}

	public Dept(String dname, String loc) {
		this.dname = dname;
		this.loc = loc;
	}

	public Integer getDeptno() {
		return this.deptno;
	}

	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return this.dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getLoc() {
		return this.loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

}
