package com.benjamin.couponProject.beans;

import java.util.Collection;

public class Company {
	
	static long id;
	private String compName;
	private String password;
	private String email;
	private Collection <Coupon> coupon;
	

	
	
	public Company (){
	
	}


	public static long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getCompName() {
		return compName;
	}


	public void setCompName(String compName) {
		this.compName = compName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Collection<Coupon> getCoupon() {
		return coupon;
	}


	public void setCoupon(Collection<Coupon> coupon) {
		this.coupon = coupon;
	}


	@Override
	public String toString() {
		return "Company [id=" + id + ", compName=" + compName + ", password=" + password + ", email=" + email
				+ ", getId()=" + getId() + ", getCompName()=" + getCompName() + ", getPassword()=" + getPassword()
				+ ", getEmail()=" + getEmail() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
	
}



