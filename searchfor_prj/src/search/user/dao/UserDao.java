package search.user.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import search.entity.User;
import search.util.DBUtil;



public class UserDao {
	
	//查找数据库中对应用户信息,实现登录功能
	public List<User> findUser() {
		Connection con = null;
		PreparedStatement pstm = null;
		List<User> users = new ArrayList<>();
		
		try {
			con = DBUtil.getCon();
			String sql = "select * from user";
			pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				String user_email = rs.getString("user_email");
				String user_tel = rs.getString("user_tel");
				String user_pwd = rs.getString("user_pwd");
				User user = new User();
				user.setUserPwd(user_pwd);
				user.setUserEmail(user_email);
				user.setUserTel(user_tel);
				users.add(user);//将从数据库中查找的所有用户信息放进users列表中
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con);
		}
		return users;
		
	}
	
	public int changeUserPwd(String tel,String pwd) {
		Connection con = null;
		PreparedStatement pstm = null;
		
		try {
			con = DBUtil.getCon();
			String sql="update user set user_pwd='"+pwd+"' where user_tel='"+tel+"'";
			pstm = con.prepareStatement(sql);
			int i = pstm.executeUpdate();
			return i;//若修改成功返回1
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con);
		}
		return 0;
	}

	
}

