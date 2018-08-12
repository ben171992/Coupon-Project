
import java.util.HashSet;
import java.util.Set;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionPool {
	
private static ConnectionPool _instance = new ConnectionPool();
private Set<Connection> _connection;

//c-tor
private ConnectionPool () {
	_connection = new HashSet<Connection>();
	try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String connectionUrl = "jdbc:sqlserver://localhost:49285;" +  
				   "databaseName=CouponDB;user=HNFHTZ1\\SQLEXPRESS;integratedSecurity=true";  
				try {
					for (int i = 0; i < 9; i++) {
//						Connection con = DriverManager.getConnection(connectionUrl);
						_connection.add(DriverManager.getConnection(connectionUrl));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
//getter
public static ConnectionPool getConnectionPool()
{
	return _instance;
}

public Connection getConnection() {
	if(_connection.iterator().next()==null)
	{
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO HANDLE EXCEPTION
			e.printStackTrace();
		}
	}
		return _connection.iterator().next();
	}
	//TODO: Exception handling


public void returnConnection(Connection conn)
{
	_connection.add(conn);
	try {
		//notify();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println("Exception no wait() to notify()");
	}
}

public void closeAllConnections() 
{
	for(Connection c: _connection)
	{
		try {
			c.close();
		} catch (SQLException e) {
			// TODO EXCEPTION HANDLING
			e.printStackTrace();
		}
	}
	
}


	
}


    


