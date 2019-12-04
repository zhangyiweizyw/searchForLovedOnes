package com.example.sunyidong.qinfeng;

import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 鑾峰彇鏁版嵁搴撹繛鎺�
	 * @return
	 */
	public static Connection getCon() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/db_searchfor?useUnicode=true&characterEncoding=utf-8","root","123");
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/*8
	 * 
	 */
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
