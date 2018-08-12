package com.benjamin.couponProject.beans;

import java.util.Date;

public class Coupon {

	private static long id;
	private String title;
	private Date startDate;
	private static Date endDate;
	private static int amount;
	private String message;
	private double price;
	private String image;
	public Enum CouponType;

	public Coupon() {

	}

	public Coupon(long id, String title, Date startDate, Date endDate, int amount, Enum couponType, String message,
			double price, String image, Enum CouponType) {
	}

	public Enum getCouponType() {
		return CouponType;
	}

	public void setCouponType(Enum couponType) {
		CouponType = couponType;
	}

	public static long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public static Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public static int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", title=" + title + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", amount=" + amount + ", message=" + message + ", price=" + price + ", image=" + image + ", getId()="
				+ getId() + ", getTitle()=" + getTitle() + ", getStartDate()=" + getStartDate() + ", getEndDate()="
				+ getEndDate() + ", getAmount()=" + getAmount() + ", getMessage()=" + getMessage() + ", getPrice()="
				+ getPrice() + ", getImage()=" + getImage() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
