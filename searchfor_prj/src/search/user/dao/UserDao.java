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
	public boolean findUser(String userTel,String password) {
		Connection con = null;
		PreparedStatement pstm = null;
	
		System.out.println("userdao"+userTel+password);
		
		try {
			con = DBUtil.getCon();
			String sql = "select user_pwd from user where user_tel = ? or (not user_tel= ? and user_email = ?)";
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, userTel);
			pstm.setString(2, userTel);
			pstm.setString(3, userTel);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("user_pwd").equals(password)) {
					System.out.println("正确");
					return true;
				}else {
					System.out.println(rs.getString("user_pwd"));
					System.out.println(password);
					System.out.println("错误");
					return false;
					
				}
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con);
		}
		return false;
	}
	
	//实现判断用户传来的电话是否已注册
	public boolean judgeUserTel(String tel) {
		Connection con = null;
		PreparedStatement pstm = null;
		
		try {
			con = DBUtil.getCon();
			String sql = "select user_tel from user where user_tel=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1,tel);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				if(!rs.getString("user_tel").equals("")) {
					return true;
				}else {
					return false;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con);
		}
		return false;
	}
	
	//实现修改密码功能
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
	//根据手机号查找用户id并返回
	public int findIdByPhone(String tel){
		Connection con=null;
		PreparedStatement pstm=null;
		int user_id=0;
		try {
			con = DBUtil.getCon();
			String sql = "select user_id from user where user_tel=?";
			pstm = con.prepareStatement(sql);
			pstm.setString(1,tel);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				user_id=rs.getInt("user_id");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con);
		}
		return user_id;
		
	}

	public int changeUserName(String tel,String name) {
		Connection con = null;
		PreparedStatement pstm = null;
		
		try {
			con = DBUtil.getCon();
			String sql="update user set user_name='"+name+"' where user_tel='"+tel+"'";
			pstm = con.prepareStatement(sql);
			int i = pstm.executeUpdate();
			return i;//鑻ヤ慨鏀规垚鍔熻繑鍥�1
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con);
		}
		return 0;
	}
	public int changeUserTel(String email,String tel) {
		Connection con = null;
		PreparedStatement pstm = null;
		
		try {
			con = DBUtil.getCon();
			String sql="update user set user_tel='"+tel+"' where user_email='"+email+"'";
			pstm = con.prepareStatement(sql);
			int i = pstm.executeUpdate();
			return i;//鑻ヤ慨鏀规垚鍔熻繑鍥�1
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con);
		}
		return 0;
	}
	
	public int changeUserEmail(String tel,String email) {
		Connection con = null;
		PreparedStatement pstm = null;
		
		try {
			con = DBUtil.getCon();
			String sql="update user set user_email='"+email+"' where user_tel='"+tel+"'";
			pstm = con.prepareStatement(sql);
			int i = pstm.executeUpdate();
			return i;//鑻ヤ慨鏀规垚鍔熻繑鍥�1
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con);
		}
		return 0;
	}
}

