package search.record.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import search.entity.OtherSearchBean;
import search.util.DBUtil;


public class OtherSearchDao {

	private static ComboPooledDataSource cpds = new ComboPooledDataSource();

	// 获得总记录数
	public int getTotalCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstl = null;
		try {
			// conn=new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "select * from other_search";
			pstl = conn.prepareStatement(sql);
			ResultSet rs = pstl.executeQuery();
			while (rs.next()) {
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("总记录数" + count);
		return count;
	}

	// 判断图片数量
	public void judgeImage(OtherSearchBean o, String[] imgpaths,int user_id) {
		int length = imgpaths.length;
		switch (length) {
		case 1:
			this.insertOne(o, imgpaths,user_id);
			break;
		case 2:
			this.insertTwo(o, imgpaths,user_id);
			break;
		case 3:
			this.insertThree(o, imgpaths,user_id);
			break;
		case 4:
			this.insertFour(o, imgpaths,user_id);
			break;
		case 5:
			this.insertFive(o, imgpaths,user_id);
			break;

		}
	}

	// 一张照片
	public void insertOne(OtherSearchBean o, String[] imgpaths,int user_id) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into other_search(s_name,s_sex,s_reason,relation,y_name,y_sex,y_age,y_email,y_phone,y_address,photo1,user_id)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setString(1, o.getS_name());
			pstl.setString(2, o.getS_sex());
			pstl.setString(3, o.getS_reason());
			pstl.setString(4, o.getRelation());
			pstl.setString(5, o.getY_name());
			pstl.setString(6, o.getY_sex());
			pstl.setInt(7, o.getY_age());
			pstl.setString(8, o.getY_email());
			pstl.setString(9, o.getY_phone());
			pstl.setString(10, o.getY_address());
			pstl.setString(11, imgpaths[0]);
			pstl.setInt(12, user_id);
			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 2
	public void insertTwo(OtherSearchBean o, String[] imgpaths,int user_id) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into other_search(s_name,s_sex,s_reason,relation,y_name,y_sex,y_age,y_email,y_phone,y_address,photo1,photo2,user_id)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setString(1, o.getS_name());
			pstl.setString(2, o.getS_sex());
			pstl.setString(3, o.getS_reason());
			pstl.setString(4, o.getRelation());
			pstl.setString(5, o.getY_name());
			pstl.setString(6, o.getY_sex());
			pstl.setInt(7, o.getY_age());
			pstl.setString(8, o.getY_email());
			pstl.setString(9, o.getY_phone());
			pstl.setString(10, o.getY_address());
			pstl.setString(11, imgpaths[0]);
			pstl.setString(12, imgpaths[1]);
			pstl.setInt(13, user_id);
			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 3
	public void insertThree(OtherSearchBean o, String[] imgpaths,int user_id) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into other_search(s_name,s_sex,s_reason,relation,y_name,y_sex,y_age,y_email,y_phone,y_address,photo1,photo2,photo3,user_id)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setString(1, o.getS_name());
			pstl.setString(2, o.getS_sex());
			pstl.setString(3, o.getS_reason());
			pstl.setString(4, o.getRelation());
			pstl.setString(5, o.getY_name());
			pstl.setString(6, o.getY_sex());
			pstl.setInt(7, o.getY_age());
			pstl.setString(8, o.getY_email());
			pstl.setString(9, o.getY_phone());
			pstl.setString(10, o.getY_address());
			pstl.setString(11, imgpaths[0]);
			pstl.setString(12, imgpaths[1]);
			pstl.setString(13, imgpaths[2]);
			pstl.setInt(14, user_id);
			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 4
	public void insertFour(OtherSearchBean o, String[] imgpaths,int user_id) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into other_search(s_name,s_sex,s_reason,relation,y_name,y_sex,y_age,y_email,y_phone,y_address,photo1,photo2,photo3,photo4,user_id)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setString(1, o.getS_name());
			pstl.setString(2, o.getS_sex());
			pstl.setString(3, o.getS_reason());
			pstl.setString(4, o.getRelation());
			pstl.setString(5, o.getY_name());
			pstl.setString(6, o.getY_sex());
			pstl.setInt(7, o.getY_age());
			pstl.setString(8, o.getY_email());
			pstl.setString(9, o.getY_phone());
			pstl.setString(10, o.getY_address());
			pstl.setString(11, imgpaths[0]);
			pstl.setString(12, imgpaths[1]);
			pstl.setString(13, imgpaths[2]);
			pstl.setString(14, imgpaths[3]);
			pstl.setInt(15, user_id);
			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 5
	public void insertFive(OtherSearchBean o, String[] imgpaths,int user_id) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into other_search(s_name,s_sex,s_reason,relation,y_name,y_sex,y_age,y_email,y_phone,y_address,photo1,photo2,photo3,photo4,photo5,user_id)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setString(1, o.getS_name());
			pstl.setString(2, o.getS_sex());
			pstl.setString(3, o.getS_reason());
			pstl.setString(4, o.getRelation());
			pstl.setString(5, o.getY_name());
			pstl.setString(6, o.getY_sex());
			pstl.setInt(7, o.getY_age());
			pstl.setString(8, o.getY_email());
			pstl.setString(9, o.getY_phone());
			pstl.setString(10, o.getY_address());
			pstl.setString(11, imgpaths[0]);
			pstl.setString(12, imgpaths[1]);
			pstl.setString(13, imgpaths[2]);
			pstl.setString(14, imgpaths[3]);
			pstl.setString(15, imgpaths[4]);
			pstl.setInt(16, user_id);
			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//获取我的发布寻人信息
		public List<OtherSearchBean> findOtherSearchBeans(int user_id) {
			Connection con = null;
			PreparedStatement pstm = null;
			List<OtherSearchBean> OtherSearchBeans = new ArrayList<>();
			
			try {
				con = DBUtil.getCon();
				String sql = "select * from other_search where user_id ="+user_id;
				pstm = con.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery();
				while(rs.next()) {
					OtherSearchBean sp=new OtherSearchBean(rs.getString("s_name"), rs.getString("s_sex") ,
							rs.getString("s_reason"), rs.getString("relation") , rs.getString("y_name") , 
							rs.getString("y_sex"), Integer.parseInt(rs.getString("y_age")) ,rs.getString("y_email") , rs.getString("y_phone") , 
							rs.getString("y_address"));
					
					OtherSearchBeans.add(sp);//将从数据库中查找的所有用户信息放进 SearchPeopleBeans列表中
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				DBUtil.close(con);
			}
			return OtherSearchBeans;
			
		}

}
