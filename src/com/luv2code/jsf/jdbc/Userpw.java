package com.luv2code.jsf.jdbc;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Userpw {
	private String Userid;
	private String Username;
	private String Password;
	public Userpw() {
	}
	
	
	public Userpw(String username, String password) {
		
		this.Username = username;
		this.Password = password;
	}


	public String getUserid() {
		return Userid;
	}


	public void setUserid(String userid) {
		Userid = userid;
	}


	public String getUsername() {
		return Username;
	}


	public void setUsername(String username) {
		Username = username;
	}


	public String getPassword() {
		return Password;
	}


	public void setPassword(String password) {
		Password = password;
	}
	
	@Override
	public String toString() {
		return "Student[username=" + Username + ", password=" + Password + "]";
	}
	
}
