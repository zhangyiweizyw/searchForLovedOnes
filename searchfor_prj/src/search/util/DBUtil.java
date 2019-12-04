package search.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getCon() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/db_searchfor?useUnicode=true&characterEncoding=utf-8","root","123");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void close(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
