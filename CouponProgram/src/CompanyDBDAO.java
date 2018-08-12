import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.benjamin.couponProject.beans.Company;
import com.benjamin.couponProject.beans.Coupon;
import com.benjamin.couponProject.beans.Customer;
import com.benjamin.couponProject.dao.CompanyDAO;
import com.benjamin.couponProject.dao.CustomerDAO;
import com.benjamin.couponProject.dao.ICouponDAO;

public class CompanyDBDAO implements CompanyDAO {

	private ConnectionPool pool;

	public void CompanyDBDAO() {

	}

	@Override
	public void createCompany(Company company) {

		Connection conn = ConnectionPool.getConnectionPool().getConnection();

		String insertTableSQL = "INSERT INTO COMPANY" + "(ID, COMP_NAME, PASSWORD, EMAIL) VALUES" + "(?,?,?,?)";

		try {
			PreparedStatement statement = conn.prepareStatement(insertTableSQL);

			statement.setLong(1, company.getId());
			statement.setString(2, company.getCompName());
			statement.setString(3, company.getPassword());
			statement.setString(4, company.getEmail());

			// execute insert SQL statement
			statement.executeUpdate();

			System.out.println("Record is inserted into COMPANY_TABLE table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		ConnectionPool.getConnectionPool().returnConnection(conn);

	}

	@Override
	public void removeCompany(Company company) {
		Connection conn = ConnectionPool.getConnectionPool().getConnection();

		String deleteSQL = "DELETE COMPANY WHERE ID = ?";

		try {
			PreparedStatement statement = conn.prepareStatement(deleteSQL);

			statement.setLong(1, company.getId());

			// execute delete SQL statement
			statement.executeUpdate();

			System.out.println("Record is deleted!");

		} catch (SQLException e) {

			System.out.println("The company wasn't deleted from the table");

		}
		ConnectionPool.getConnectionPool().returnConnection(conn);

	}

	@Override
	public void updateCompany(Company company) {

		Connection conn = ConnectionPool.getConnectionPool().getConnection();

		String updateTableSQL = "UPDATE COMPANY SET ID = ? , COMP_NAME = ? , PASSWORD = ? , EMAIL = ? "
				+ "WHERE ID = ?";

		try {
			PreparedStatement statement = conn.prepareStatement(updateTableSQL);

			statement.setLong(1, company.getId());
			statement.setString(2, company.getCompName());
			statement.setString(3, company.getPassword());
			statement.setString(4, company.getEmail());
			statement.setLong(5, company.getId());

			// execute update SQL statement
			statement.executeUpdate();

			System.out.println("Record is updated to COMPANY table!");

		} catch (SQLException e) {

			System.out.println("The record haven't been updated");

		}
		ConnectionPool.getConnectionPool().returnConnection(conn);

	}

	@Override
	public Company getCompany(long id) {

		try {

			Connection conn = ConnectionPool.getConnectionPool().getConnection();

			// create SQL query to fetch particular record
			String sqlSelectQuery = "SELECT * FROM COMPANY WHERE ID = ?";

			// Creating JDBC Statement
			PreparedStatement statement = conn.prepareStatement(sqlSelectQuery);

			// set values for PreparedStatement for respective ?
			statement.setLong(1, Company.getId()); // COMPANY_ID

			// Step 2.C: Executing SQL & retrieve data into ResultSet
			ResultSet resultSet = statement.executeQuery();

			// processing returned data and printing into console
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1));

			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
		return null;

	}

	@Override
	public List<String> getAllCompanies() {

		Connection conn = ConnectionPool.getConnectionPool().getConnection();

		List<String> CompanyCollection = new ArrayList<String>();

		try {
			// create SQL query to fetch all player records
			String sqlSelectAllQuery = "SELECT * FROM COMPANY";

			// Step 2.B: Creating JDBC Statement
			PreparedStatement statement = conn.prepareStatement(sqlSelectAllQuery);

			// Step 2.C: Executing SQL & retrieve data into ResultSet
			ResultSet resultSet = statement.executeQuery();

			// processing returned data and gathering into the CompanyCollection
			while (resultSet.next()) {
				String CompanyName = resultSet.getString("COMP_NAME");
				CompanyCollection.add(CompanyName);

			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}

		return CompanyCollection;
	}

	@Override
	public List<Coupon> getCoupons() { // Il faut appeler les informations
										// Coupons de la DB,
										// et en faire des objets avec un
										// constructeur

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

			// Ici il faut que je rentre mes donnés de db dans les objets

			while (resultSet.next()) {
				long id = resultSet.getLong("ID");
				String title = resultSet.getString("TITLE");
				Date startDate = resultSet.getDate("START_DATE");
				Date endDate = resultSet.getDate("END_DATE");
				int amount = resultSet.getInt("AMOUNT");
				Enum CouponType = Enum.valueOf(null, resultSet.getString("COUPON_TYPE"));
				String message = resultSet.getString("MESSAGE");
				double price = resultSet.getDouble("PRICE");
				String image = resultSet.getString("IMAGE");

				Coupon coupon = new Coupon(id, title, startDate, endDate, amount, CouponType, message, price, image,
						CouponType);

				CouponList.add(coupon);

			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}

		return CouponList;
	}

	@Override
	public boolean login(String compName, String password) {

		long log = 0;
		Connection conn = ConnectionPool.getConnectionPool().getConnection();

		try {
			PreparedStatement stm = conn.prepareStatement("SELECT * FROM COMPANY WHERE COMP_NAME =?");
			stm.setString(1, compName);
			ResultSet companyRow = stm.executeQuery();
			companyRow.next();
			if (!companyRow.getString("PASSWORD").equals(password)) {
				log = 0;
			} else {
				log = companyRow.getLong("ID");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Username or password is incorrect. Please try again ");

		}
		return false;

	}

}
