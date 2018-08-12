package com.benjamin.couponProject.beans;

import java.util.Collection;

public class Customer {
	
	private static long id ;
	private String custName ;
	private String password ;
 	private Collection <Coupon> coupon;
 	
	public Customer (){
		
	}

	public static long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Coupon> getCoupon() {
		return coupon;
	}

	public void setCoupon(Collection<Coupon> coupon) {
		this.coupon = coupon;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", custName=" + custName + ", password=" + password + ", getId()=" + getId()
				+ ", getCustName()=" + getCustName() + ", getPassword()=" + getPassword() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
