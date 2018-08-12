import java.util.Collection;
import java.util.List;

import com.benjamin.couponProject.beans.Company;
import com.benjamin.couponProject.beans.Coupon;
import com.benjamin.couponProject.dao.CouponType;
import com.benjamin.couponProject.dao.ICouponDAO;

public class CompanyFacade {
	private ICouponDAO couponDao = new CouponDBDAO(); /* ici je créé un objet
														 depuis ma class pour
														 l'utiliser */

	public CompanyFacade() {
	}

	public void createCoupon(Coupon coupon) throws Exception { 
		if (couponDao.getCoupon(coupon.getId()) != null) {

			throw new Exception("Failed to create Coupon." + " Adding a Coupon with an existing name can't be done.");
		} else {
			couponDao.createCoupon(coupon);
		}
	}

	public void removeCoupon(Coupon coupon) {
		if (couponDao.getCoupon(coupon.getId()) != null) {
			couponDao.removeCoupon(coupon);
		} else {
			System.out.println("The coupon doesn't exist, than can't be removed");
		}
	}

	public void updateCoupon(Coupon coupon) {
		if (couponDao.getCoupon(coupon.getId()) != null) {
			couponDao.updateCoupon(coupon);
		} else {
			System.out.println("Can't update an unexisting coupon.");
		}
	}

	public Coupon getCoupon(long id) {

		return couponDao.getCoupon(id);
	}

	public Collection<String> getAllCoupon() {
		return couponDao.getAllCoupon();
	}

	public Collection<Coupon> getCouponByType(CouponType coupontype) {
		return couponDao.getCouponByType(coupontype);
	}

	public void login(String compName, String password, String clientType) {

	}

}
