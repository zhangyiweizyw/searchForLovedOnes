package search.register.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import search.util.DBUtil;

public class RegisterDao {
	//向数据库中注册（添加）用户，实现注册功能
		public void addUser(String user_name,String user_pwd,String user_type,String user_email,String user_tel) {
			Connection con = null;
			PreparedStatement pstm = null;
			
			try {
				con = DBUtil.getCon();
				String sql="insert into user(user_name,user_pwd,user_type,user_email,user_tel) values('"+user_name+"','"+user_pwd+"','"+user_type+"','"+user_email+"','"+user_tel+"')";
				pstm = con.prepareStatement(sql);
				
				int i = pstm.executeUpdate();
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				DBUtil.close(con);
			}
		}
}
