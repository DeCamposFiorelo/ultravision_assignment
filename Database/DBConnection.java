package Database;

import java.sql.Connection;
import java.sql.DriverManager;


// this class is for database connection

public class DBConnection { 
	
	static Connection conn = null;
	
	
	public static Connection getDBConnection() {
		try {
			if (conn ==null) {
				
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ultravision","root","");			
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return conn;
		
		
	}

}
