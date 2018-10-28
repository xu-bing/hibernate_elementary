package com.entity;

import java.io.Serializable;

/**
 * 用来表示联合主键
 * 1) 实现Serializable接口
 * 2) 重写equals及hashCode
 *
 */
public class Key  implements Serializable{
	private String id;
	private String name;
	public Key() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Key(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null){
			return false;
		}
		
		if (!(arg0 instanceof Key)){
			return false;
		}
		
		Key k = (Key)arg0;
		
		if (k.getId().equals(this.id) && k.getName().equals(this.name)){
			return true;
		} else {
			return false;
		}
			
	}	//equals
	
	@Override
	public int hashCode() {
		return 12 * Integer.parseInt(id);
	}	
}
