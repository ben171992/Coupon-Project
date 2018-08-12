package com.benjamin.couponProject.dao;

import java.util.Collection;
import java.util.List;

import com.benjamin.couponProject.beans.Company;
import com.benjamin.couponProject.beans.Coupon;
import com.benjamin.couponProject.beans.Customer;

public interface CustomerDAO {
	
	public void createCustomer (Customer customer);
	public void removeCustomer (Customer customer);
	public void updateCustomer (Customer customer);
	public Customer getCustomer (long id);
	public List<String> getAllCustomer();
	public Collection<Coupon> getCoupons();
	public boolean login(String custName, String password);
	
	
}

