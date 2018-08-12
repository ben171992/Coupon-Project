import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.benjamin.couponProject.beans.Company;
import com.benjamin.couponProject.beans.Coupon;
import com.benjamin.couponProject.dao.CouponType;
import com.benjamin.couponProject.dao.ICouponDAO;

public class CouponDBDAO implements ICouponDAO {

	private ConnectionPool pool;
	public List<String> CouponCollection;

	public void CouponDBDAO() {

	}

	@Override
	public void createCoupon(Coupon coupon) {

		Connection conn = ConnectionPool.getConnectionPool().getConnection();

		String insertTableSQL = "INSERT INTO COUPON"
				+ "(ID, TITLE, START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE) VALUES"
				+ "(?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement statement = conn.prepareStatement(insertTableSQL);

			statement.setLong(1, coupon.getId());
			statement.setString(2, coupon.getTitle());
			statement.setDate(3, (Date) coupon.getStartDate());
			statement.setDate(4, (Date) coupon.getEndDate());
			statement.setInt(5, coupon.getAmount());
			statement.setString(6, String.valueOf(coupon.getCouponType()));
			statement.setString(7, coupon.getMessage());
			statement.setDouble(8, coupon.getPrice());
			statement.setString(9, coupon.getImage());

			// execute insert SQL statement
			statement.executeUpdate();

			System.out.println("Record is inserted into COMPANY_TABLE table!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		ConnectionPool.getConnectionPool().returnConnection(conn);

	}

	@Override
	public void removeCoupon(Coupon coupon) {

		Connection conn = ConnectionPool.getConnectionPool().getConnection();

		String deleteSQL = "DELETE COUPON WHERE ID = ?";

		try {
			PreparedStatement statement = conn.prepareStatement(deleteSQL);

			statement.setLong(1, coupon.getId());

			// execute delete SQL statement
			statement.executeUpdate();

			System.out.println("Record is deleted!");

		} catch (SQLException e) {

			System.out.println("The coupon wasn't deleted from the table");

		}
		ConnectionPool.getConnectionPool().returnConnection(conn);

	}

	@Override
	public void updateCoupon(Coupon coupon) {

		Connection conn = ConnectionPool.getConnectionPool().getConnection();

		String updateTableSQL = "INSERT INTO COUPON"
				+ "(ID, TITLE, START_DATE, END_DATE, AMOUNT, TYPE, MESSAGE, PRICE, IMAGE) VALUES"
				+ "(?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement statement = conn.prepareStatement(updateTableSQL);

			statement.setLong(1, coupon.getId());
			statement.setString(2, coupon.getTitle());
			statement.setDate(3, (Date) coupon.getStartDate());
			statement.setDate(4, (Date) coupon.getEndDate());
			statement.setInt(5, coupon.getAmount());
			statement.setString(6, String.valueOf(coupon.getCouponType()));
			statement.setString(7, coupon.getMessage());
			statement.setDouble(8, coupon.getPrice());
			statement.setString(9, coupon.getImage());

			// execute update SQL statement
			statement.executeUpdate();

			System.out.println("Record is updated to COUPON table!");

		} catch (SQLException e) {

			System.out.println("The record haven't been updated");

		}
		ConnectionPool.getConnectionPool().returnConnection(conn);

	}

	@Override
	public Coupon getCoupon(long id) {

		try {

			Connection conn = ConnectionPool.getConnectionPool()
					.getConnection(); /*
										 * c'est la qu'est le probleme: il ne
										 * faut pas afficher les coupons, il
										 * faut les mettre un par un dans des
										 * objets
										 */

			// create SQL query to fetch particular record
			String sqlSelectQuery = "SELECT * FROM COUPON WHERE ID = ?";

			// Creating JDBC Statement
			PreparedStatement statement = conn.prepareStatement(sqlSelectQuery);

			// set values for PreparedStatement for respective ?
			statement.setLong(1, Coupon.getId()); // ID

			// Step 2.C: Executing SQL & retrieve data into ResultSet
			ResultSet resultSet = statement.executeQuery();

			// processing returned data and printing into console
			while (resultSet.next()) {
				System.out.println(resultSet.getInt(1)); // ici il faut les
															// mettre un par un
															// dans un objet

			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
		return null;

	}

	@Override
	public Collection<String> getAllCoupon() {

		Connection conn = ConnectionPool.getConnectionPool().getConnection();

		CouponCollection = new ArrayList<String>();

		try {
			// create SQL query to fetch all player records
			String sqlSelectAllQuery = "SELECT * FROM COUPON";

			// Step 2.B: Creating JDBC Statement
			PreparedStatement statement = conn.prepareStatement(sqlSelectAllQuery);

			// Step 2.C: Executing SQL & retrieve data into ResultSet
			ResultSet resultSet = statement.executeQuery();

			// processing returned data and gathering into the CompanyCollection
			while (resultSet.next()) {
				String CompanyName = resultSet.getString("COMP_NAME");
				CouponCollection.add(CompanyName);

			}
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}

		return CouponCollection;

	}

	@Override
	public Collection<Coupon> getCouponByType(CouponType coupontype) {

		Connection conn = ConnectionPool.getConnectionPool().getConnection();

		// Creating List in order to stock the COUPON Data from the DB
		List<Coupon> CouponTypeList = new ArrayList<Coupon>();

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

				CouponTypeList.add(coupon);

			}

			/*
			 * Ici je pense qu'il faut faire des If Else pour checker le type du
			 * coupon et ensuite le rentrer dans une sous liste a checker
			 * comment faire
			 */

			List<Object> CouponRestaurants = new ArrayList<Object>(); // list
																		// for
																		// Coupon
																		// type
																		// Restaurant
			List<Object> CouponElectricity = new ArrayList<Object>(); // list
																		// for
																		// Coupon
																		// type
																		// Electricity
			List<Object> CouponFood = new ArrayList<Object>(); // list for
																// Coupon type
																// Food
			List<Object> CouponHealth = new ArrayList<Object>(); // list for
																	// Coupon
																	// type
																	// Health
			List<Object> CouponSports = new ArrayList<Object>(); // list for
																	// Coupon
																	// type
																	// Sports
			List<Object> CouponCamping = new ArrayList<Object>(); // list for
																	// Coupon
																	// type
																	// Camping
			List<Object> CouponTraveling = new ArrayList<Object>(); // list for
																	// Coupon
																	// type
																	// Traveling

			for (Coupon c : CouponTypeList) { /*
												 * my loop FOR, in order to
												 * check the TYPE of the OBJECT
												 * and to put it in the relevant
												 * LIST
												 */

				if (c.CouponType == CouponType.Restaurants) { // check the TYPE
																// of the
																// inserted
																// OBJECT (c)

					CouponRestaurants.add(c); // put into the Restaurants List
												// (CouponRestaurants)

				}
				if (c.CouponType == CouponType.Electricity) {

					CouponElectricity.add(c);

				}
				if (c.CouponType == CouponType.Food) {

					CouponFood.add(c);

				}
				if (c.CouponType == CouponType.Health) {

					CouponHealth.add(c);

				}
				if (c.CouponType == CouponType.Sports) {

					CouponSports.add(c);

				}
				if (c.CouponType == CouponType.Camping) {

					CouponCamping.add(c);

				}
				if (c.CouponType == CouponType.Traveling) {

					CouponTraveling.add(c);

				}

			}

		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}

		return CouponTypeList;

	}

	public static void removeCouponLong(long id) {

		Connection conn = ConnectionPool.getConnectionPool().getConnection();

		String deleteSQL = "DELETE COUPON WHERE ID = ?";

		try {
			PreparedStatement statement = conn.prepareStatement(deleteSQL);

			statement.setLong(1, Coupon.getId());

			// execute delete SQL statement
			statement.executeUpdate();

			System.out.println("Record is deleted!");

		} catch (SQLException e) {

			System.out.println("The coupon wasn't deleted from the table");

		}
		ConnectionPool.getConnectionPool().returnConnection(conn);

	}

	public void addCouponToCustomer(long id, ICouponDAO couponDao) {

	}
}
