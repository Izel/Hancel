package org.hansel.myAlert;

import java.io.Serializable;

public class Ring implements Serializable{
	
	private static final long serialVersionUID = -2188411347965052790L;
	private String id;
	private String name;
	private long always;
	
	
	public Ring(){
		id = null;
		name = null;
	}
	public Ring(String id, String name, long always){
		this.id = id;
		this.name = name;
		this.always = always;
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
	public long getAlways() {
		return always;
	}
	public void setAlways(long always) {
		this.always = always;
	}
}