import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.benjamin.couponProject.beans.Company;
import com.benjamin.couponProject.beans.Coupon;
import com.benjamin.couponProject.beans.Customer;
import com.benjamin.couponProject.dao.CouponType;
import com.benjamin.couponProject.dao.CustomerDAO;

public class CustomerDBDAO implements CustomerDAO {
	
	private ConnectionPool pool;
	
public void CustomerDBDAO() {
		
	}

	@Override
	public void createCustomer(Customer customer) {
		
		Connection conn = ConnectionPool.getConnectionPool().getConnection();
		
		 String insertTableSQL = "INSERT INTO CUSTOMER"
		+ "(ID, CUST_NAME, PASSWORD) VALUES"
		+ "(?,?,?)";

try {
	PreparedStatement statement = conn.prepareStatement(insertTableSQL);
	
	statement.setLong(1, customer.getId());
	statement.setString(2, customer.getCustName());
	statement.setString(3, customer.getPassword());

	// execute insert SQL statement
	statement.executeUpdate();

	System.out.println("Record is inserted into CUSTOMER_TABLE table!");
	
} 
catch (SQLException e) {

	System.out.println(e.getMessage()); 
	
}
		
ConnectionPool.getConnectionPool().returnConnection(conn);
	}
	
	
	

	@Override
	public void removeCustomer(Customer customer) {

Connection conn = ConnectionPool.getConnectionPool().getConnection();
		
		String deleteSQL = "DELETE CUSTOMER WHERE ID = ?";

		try {
			PreparedStatement statement = conn.prepareStatement(deleteSQL);
			
			statement.setLong(1, customer.getId());

			// execute delete SQL statement
			statement.executeUpdate();

			System.out.println("Record is deleted!");
			
		} catch (SQLException e) {

			System.out.println("The customer wasn't deleted from the table");

		}
		ConnectionPool.getConnectionPool().returnConnection(conn);

	}
		

	@Override
	public void updateCustomer(Customer customer) {
		
Connection conn = ConnectionPool.getConnectionPool().getConnection();
		
		String updateTableSQL = "UPDATE CUSTOMER SET ID = ? , CUST_NAME = ? , PASSWORD = ?"  + "WHERE ID = ?";

		try {
			PreparedStatement statement = conn.prepareStatement(updateTableSQL);
		
			statement.setLong(1, customer.getId());
			statement.setString(2, customer.getCustName());
			statement.setString(3, customer.getPassword());
			statement.setLong(4, customer.getId());
		
		// execute update SQL statement
		statement.executeUpdate();
		
		System.out.println("Record is updated to COMPANY table!");
		
		} catch (SQLException e) {
		
		System.out.println("The record haven't been updated");
		
		}
		ConnectionPool.getConnectionPool().returnConnection(conn);

		
		
	}

	@Override
	public Customer getCustomer(long id) {
		try {
			 
			Connection conn = ConnectionPool.getConnectionPool().getConnection();

 
            // create SQL query to fetch particular record
            String sqlSelectQuery = 
                           "SELECT * FROM CUSTOMER WHERE ID = ?";
 
            //Creating JDBC Statement 
            PreparedStatement statement = conn.prepareStatement(sqlSelectQuery);
 
            // set values for PreparedStatement for respective ?
            statement.setLong(1, Customer.getId()); // COMPANY_ID
           
         // Step 2.C: Executing SQL & retrieve data into ResultSet
            ResultSet resultSet = statement.executeQuery();
 
            // processing returned data and printing into console
            while(resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                        
            }
        }
        catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
		return null;
	}
	
	

	@Override
	public List<String> getAllCustomer() {
		
		Connection conn = ConnectionPool.getConnectionPool().getConnection();
		
		List<String> CustomerCollection = new ArrayList<String>();
		
		try {
		// create SQL query to fetch all player records
        String sqlSelectAllQuery = "SELECT * FROM CUSTOMER"; 

        // Step 2.B: Creating JDBC Statement 
        PreparedStatement statement = conn.prepareStatement(sqlSelectAllQuery);

        // Step 2.C: Executing SQL & retrieve data into ResultSet
        ResultSet resultSet = statement.executeQuery();
        
        // processing returned data and gathering into the CompanyCollection
        while(resultSet.next()) {
        	String CustomerName = resultSet.getString("COMP_NAME");
        	CustomerCollection.add(CustomerName);
        	
        }
    }
    catch(SQLException sqlex){
        sqlex.printStackTrace();
    }
		ConnectionPool.getConnectionPool().returnConnection(conn);
	
		return CustomerCollection;  
	}

	

	@Override
	public Collection<Coupon> getCoupons() {
		
Connection conn = ConnectionPool.getConnectionPool().getConnection();
		
        // Creating List in order to stock the COUPON Data from the DB
		List<Coupon> CouponList = new ArrayList<Coupon>();
		
		try {
			// create SQL query to fetch all player records
	        String sqlSelectAllCoupon = "SELECT * FROM COUPON";
	
	        // Step 2.B: Creating JDBC Statement 
	        PreparedStatement statement = conn.prepareStatement(sqlSelectAllCoupon);
	
	        // Step 2.C: Executing SQL & retrieve data into ResultSet
	        ResultSet resultSet = statement.executeQuery();	        
	        
	        while(resultSet.next()) {
	        	long id = resultSet.getLong("ID");
	        	String title = resultSet.getString("TITLE");
	        	Date startDate = resultSet.getDate("START_DATE");
	        	Date endDate = resultSet.getDate("END_DATE");
	        	int amount = resultSet.getInt("AMOUNT");
	        	Enum CouponType =  Enum.valueOf(null, resultSet.getString("COUPON_TYPE"));
	        	String message = resultSet.getString("MESSAGE"); 
	        	double price = resultSet.getDouble("PRICE");
	        	String image = resultSet.getString("IMAGE"); 

	        	  
	        	  Coupon coupon = new Coupon(id, title, startDate, endDate, amount, CouponType, message, price, image, CouponType);
	        	  
	        	  CouponList.add(coupon);  
	        	
	        }
	    }
	    catch(SQLException sqlex){
	        sqlex.printStackTrace();
	    }
		
		ConnectionPool.getConnectionPool().returnConnection(conn);

		return CouponList;
	}

	@Override
	public boolean login(String custName, String password) {
		
		long log = 0;
		Connection conn = ConnectionPool.getConnectionPool().getConnection();
		
		try {
			PreparedStatement stm =conn.prepareStatement("SELECT * FROM COMPANY WHERE COMP_NAME =?");
			stm.setString(1, custName);
			ResultSet companyRow = stm.executeQuery();
			companyRow.next();
			if(!companyRow.getString("PASSWORD").equals(password))
			{
				log = 0;
			}
			else 
			{
				log=companyRow.getLong("ID");
			}
		} catch (SQLException e) {
			System.out.println("Username or password is incorrect. Please try again ");
			
		}
		return false;
		
	}
	
	public static Collection <Coupon> getCustomersCouponsByPrice(long id, double price){
		ArrayList<Coupon> custCoupons = new ArrayList<Coupon>();
		Connection conn = ConnectionPool.getConnectionPool().getConnection();
		try {
			PreparedStatement stm = conn.prepareStatement("select COUPON_ID from COUPON WHERE CUSTOMER_ID=? AND PRICE<=?");
			stm.setLong(1, id);
			stm.setDouble(2, price);
			ResultSet couponRow = stm.executeQuery();
			
			while (couponRow.next()) {
			long couponId= couponRow.getLong("COUPON_ID");
				custCoupons.add(new CouponDBDAO().getCoupon(couponId));
			}
		} catch (SQLException e) {
			System.out.println("No Coupons in this COUPON Table ");
		}
		ConnectionPool.getConnectionPool().returnConnection(conn);

		
		
		return custCoupons;
	}
	
	
	public Collection <Coupon> getCustomersCouponsByType(long id, CouponType type){
		ArrayList<Coupon> custCoupons = new ArrayList<Coupon>();
		Connection conn = ConnectionPool.getConnectionPool().getConnection();
		try {
			PreparedStatement stm = conn.prepareStatement("select COUPON_ID from COUPON WHERE CUSTOMER_ID=? AND TYPE=?");
			stm.setLong(1, id);
			stm.setString(2, type.name());
			ResultSet couponRow = stm.executeQuery();
			
			while (couponRow.next()) {
			long couponId= couponRow.getLong("COUPON_ID");
				custCoupons.add(new CouponDBDAO().getCoupon(couponId));
			}
		} catch (SQLException e) {
			System.out.println("No Coupons in this COUPON Table ");
		}
		ConnectionPool.getConnectionPool().returnConnection(conn);

		return custCoupons;
	}

	}
	
	
		
	