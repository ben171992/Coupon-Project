package com.benjamin.couponProject.dao;

import java.util.Collection;
import com.benjamin.couponProject.beans.Coupon;

public interface ICouponDAO {
	public void createCoupon(Coupon coupon);

	public void removeCoupon(Coupon coupon);

	public void updateCoupon(Coupon coupon);

	public Coupon getCoupon(long id);

	public Collection<String> getAllCoupon();

	public Collection<Coupon> getCouponByType(CouponType coupontype);

}// IcouponDAO