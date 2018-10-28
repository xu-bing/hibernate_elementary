package com.entity;

public class Unionkey implements java.io.Serializable {

	private Key key;	// ÁªºÏÖ÷¼ü
	private String sex;

	public Unionkey() {
	}

	public Unionkey(Key key, String sex) {
		super();
		this.key = key;
		this.sex = sex;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	

}
