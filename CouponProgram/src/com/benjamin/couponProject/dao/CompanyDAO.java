package com.benjamin.couponProject.dao;

import java.util.Collection;
import java.util.List;

import com.benjamin.couponProject.beans.Company;
import com.benjamin.couponProject.beans.Coupon;
import com.benjamin.couponProject.beans.Customer;

public interface CompanyDAO {
	
	public void createCompany (Company company);
	public void removeCompany (Company company);
	public void updateCompany (Company company);
	public Company getCompany (long id);
	public List<String> getAllCompanies();
	public List<Coupon> getCoupons();
	public boolean login(String compName, String password);
	
	
}