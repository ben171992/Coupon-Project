import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.benjamin.couponProject.beans.Company;
import com.benjamin.couponProject.beans.Coupon;
import com.benjamin.couponProject.beans.Customer;
import com.benjamin.couponProject.dao.CompanyDAO;
import com.benjamin.couponProject.dao.CouponType;
import com.benjamin.couponProject.dao.CustomerDAO;
import com.benjamin.couponProject.dao.ICouponDAO;

public class CustomerFacade {
	private ICouponDAO couponDao = new CouponDBDAO();
	private CustomerDBDAO customerDBDAO = new CustomerDBDAO();
	private Customer currentCust;
	private Customer customer;

	public CustomerFacade() {

	}

	public void purchaseCoupon(Coupon coupon) {
		// check if the coupon exists and how much
		if (couponDao.getCoupon(Coupon.getId()) != null && Coupon.getAmount() > 0) {
			// check if the coupon is already in the customer's hand
			if (!((CustomerDAO) currentCust).getCoupons().contains(couponDao)) {
				((CouponDBDAO) couponDao).addCouponToCustomer(currentCust.getId(), couponDao);
			}
		}
	}

	public void getAllPurchasedCoupons() {
		Collection<Coupon> customerCoupons = new ArrayList<Coupon>();
		customerCoupons = customer.getCoupon();
		for (Coupon c : customerCoupons) {
			System.out.println(c);
		}
	}

	public void getAllPurchasedCouponsByType(CouponType type) {  
		
			Collection <Coupon> custCoupons = new ArrayList<Coupon>();
			custCoupons = customerDBDAO.getCustomersCouponsByType(currentCust.getId(), type); 
			for(Coupon c: custCoupons) {
				System.out.println(c);
			}
		/*Il faut faire un for(mes
													 objects de la liste) {
													 if( Enum = Sport) {
													 mets le dans la liste
													 CouponTypeSport; } } 
		// creating an arrayList in order to stock the aimed coupons
		Collection<Coupon> PurchasedCouponByTypeSport = new ArrayList<Coupon>(); //Liste des coupons acheté de la categorie Sport
		Collection<Coupon> PurchasedCouponByTypeRestaurants = new ArrayList<Coupon>();
		Collection<Coupon> PurchasedCouponByTypeElectricity = new ArrayList<Coupon>();
		Collection<Coupon> PurchasedCouponByTypeFood = new ArrayList<Coupon>();
		Collection<Coupon> PurchasedCouponByTypeHealth = new ArrayList<Coupon>();
		Collection<Coupon> PurchasedCouponByTypeCamping = new ArrayList<Coupon>();
		Collection<Coupon> PurchasedCouponByTypeTraveling = new ArrayList<Coupon>();
		
		CouponDBDAO c = new CouponDBDAO();
		
		int collectionSize = c.CouponCollection.size();

		//for every object I'm checking if the object is from the Enum.Sport et je le mets dans la liste correspondante.
		for (int i = 0; i < collectionSize ; i++) {

			Coupon c1 = new Coupon();
			
			if (c1.CouponType == Enum.valueOf(null, "Sports")) {
				PurchasedCouponByTypeSport.add(c1);
			}
			if (c1.CouponType == Enum.valueOf(null, "Restaurants")) {
				PurchasedCouponByTypeRestaurants.add(c1);
			}
			if (c1.CouponType == Enum.valueOf(null, "Electricity")) {
				PurchasedCouponByTypeElectricity.add(c1);
			}
			if (c1.CouponType == Enum.valueOf(null, "Food")) {
				PurchasedCouponByTypeFood.add(c1);
			}
			if (c1.CouponType == Enum.valueOf(null, "Health")) {
				PurchasedCouponByTypeHealth.add(c1);
			}
			if (c1.CouponType == Enum.valueOf(null, "Camping")) {
				PurchasedCouponByTypeCamping.add(c1);
			}
			if (c1.CouponType == Enum.valueOf(null, "Traveling")) {
				PurchasedCouponByTypeTraveling.add(c1);
			}
		}*/

	}

	public void getAllPurchasedCouponsByPrice(double price) {
		Collection <Coupon> custCoupons = new ArrayList<Coupon>();
		custCoupons = CustomerDBDAO.getCustomersCouponsByPrice(currentCust.getId(), price); 
		for(Coupon c: custCoupons) {
			System.out.println(c);
		}
		
	}

	public void login(String compName, String password, String clientType) {
		
	}

}
