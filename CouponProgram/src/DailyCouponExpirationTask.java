import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import com.benjamin.couponProject.beans.Coupon;
import com.benjamin.couponProject.dao.CompanyDAO;

public class DailyCouponExpirationTask extends Thread {

    // class fields
    private boolean keepRunning;
    private final long sleepTime;
    private CompanyDBDAO companyDBDAO;
    private CouponDBDAO couponDBDAO;
    private CustomerDBDAO customerDBDAO;

    // constructor
    DailyCouponExpirationTask() {
        keepRunning = true;
        this.sleepTime = 24 * 60 * 60 * 1000;
        companyDBDAO = new CompanyDBDAO();
        couponDBDAO = new CouponDBDAO();
        customerDBDAO = new CustomerDBDAO();
    } // end constructor

    // class methods
    // force the thread to stop
    void stopRunning() {
        keepRunning = false;

        interrupt();
    } // end method stopRunning

    // Runnable interface methods
    // run 
    @Override
    public void run() {
        while (keepRunning) {
            Date currentDate = new Date(new java.util.Date().getTime());
            Collection<String> coupons = couponDBDAO.getAllCoupon();

            Iterator<String> iterator = coupons.iterator();

            while (iterator.hasNext()) {
                String currentCoupon = iterator.next();

                if (currentDate.after(Coupon.getEndDate())) { // tout cette page je l'ai trouvé sur StackOverFlow, c'est dans les favoris
                												// si ca ne marche pas, c'est que le pb vient du Coupon de Coupon.getEndDate()
                    // remove coupon from database
                }
            }

            try {
                Thread.sleep(sleepTime); 
            } catch (InterruptedException e) {
                if (keepRunning) {
                    e.printStackTrace();
                }
            }
        }
    } // end method run

} // end class DailyCouponExpirationTask
