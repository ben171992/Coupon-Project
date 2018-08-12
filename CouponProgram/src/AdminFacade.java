import java.util.Collection;
import java.util.List;

import com.benjamin.couponProject.beans.Company;
import com.benjamin.couponProject.beans.Coupon;
import com.benjamin.couponProject.beans.Customer;
import com.benjamin.couponProject.dao.CompanyDAO;
import com.benjamin.couponProject.dao.CustomerDAO;
import com.benjamin.couponProject.dao.ICouponDAO;

public class AdminFacade {

	private CustomerDBDAO customerDao = new CustomerDBDAO();
	private CompanyDBDAO companyDao = new CompanyDBDAO();

	public AdminFacade() {

	}

	public void createCompany(Company company) {
		if ((companyDao.getCompany(company.getId()) != null)
				// && (companyDao.getCompany(company.getCompName())) == null
				) {
			
			companyDao.createCompany(company);
		} else {
			System.out.println("A company with this ID already exists");
		}

	}

	public void removeCompany(Company company) {
		CouponDBDAO.removeCouponLong(Coupon.getId());	// a regler demain -----> regler!
		companyDao.removeCompany(company);

	}

	public void updateCompany(Company company) {
		if (companyDao.getCompany(company.getId()) != null) {
			companyDao.updateCompany(company);
		}
	}

	public Company getCompany(long id) {

		return companyDao.getCompany(id);
	}

	public List<String> getAllCompanies() {
		return companyDao.getAllCompanies();
	}

	public void createCustomer(Customer customer) {
		customerDao.createCustomer(customer); 
	}

	public void removeCustomer(Customer customer) {
		customerDao.removeCustomer(customer);
	}

	public void updateCustomer(Customer customer) {
		customerDao.updateCustomer(customer);
	}

	public Customer getCustomer(long id) {
		return customerDao.getCustomer(id);

	}

	public List<String> getAllCustomer() {
		return customerDao.getAllCustomer();
	}

	public void login(String compName, String password, String clientType) {

	}
}
