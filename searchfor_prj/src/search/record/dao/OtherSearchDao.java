package search.record.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import search.entity.OtherSearchBean;


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
	public void judgeImage(OtherSearchBean o, String[] imgpaths) {
		int length = imgpaths.length;
		switch (length) {
		case 1:
			this.insertOne(o, imgpaths);
			break;
		case 2:
			this.insertTwo(o, imgpaths);
			break;
		case 3:
			this.insertThree(o, imgpaths);
			break;
		case 4:
			this.insertFour(o, imgpaths);
			break;
		case 5:
			this.insertFive(o, imgpaths);
			break;

		}
	}

	// 一张照片
	public void insertOne(OtherSearchBean o, String[] imgpaths) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into other_search(id,s_name,s_sex,s_reason,relation,y_name,y_sex,y_age,y_email,y_phone,y_address,photo1)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setInt(1, id);
			pstl.setString(2, o.getS_name());
			pstl.setString(3, o.getS_sex());
			pstl.setString(4, o.getS_reason());
			pstl.setString(5, o.getRelation());
			pstl.setString(6, o.getY_name());
			pstl.setString(7, o.getY_sex());
			pstl.setInt(8, o.getY_age());
			pstl.setString(9, o.getY_email());
			pstl.setString(10, o.getY_phone());
			pstl.setString(11, o.getY_address());
			pstl.setString(12, imgpaths[0]);

			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 2
	public void insertTwo(OtherSearchBean o, String[] imgpaths) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into other_search(id,s_name,s_sex,s_reason,relation,y_name,y_sex,y_age,y_email,y_phone,y_address,photo1,photo2)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setInt(1, id);
			pstl.setString(2, o.getS_name());
			pstl.setString(3, o.getS_sex());
			pstl.setString(4, o.getS_reason());
			pstl.setString(5, o.getRelation());
			pstl.setString(6, o.getY_name());
			pstl.setString(7, o.getY_sex());
			pstl.setInt(8, o.getY_age());
			pstl.setString(9, o.getY_email());
			pstl.setString(10, o.getY_phone());
			pstl.setString(11, o.getY_address());
			pstl.setString(12, imgpaths[0]);
			pstl.setString(13, imgpaths[1]);
			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 3
	public void insertThree(OtherSearchBean o, String[] imgpaths) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into other_search(id,s_name,s_sex,s_reason,relation,y_name,y_sex,y_age,y_email,y_phone,y_address,photo1,photo2,photo3)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setInt(1, id);
			pstl.setString(2, o.getS_name());
			pstl.setString(3, o.getS_sex());
			pstl.setString(4, o.getS_reason());
			pstl.setString(5, o.getRelation());
			pstl.setString(6, o.getY_name());
			pstl.setString(7, o.getY_sex());
			pstl.setInt(8, o.getY_age());
			pstl.setString(9, o.getY_email());
			pstl.setString(10, o.getY_phone());
			pstl.setString(11, o.getY_address());
			pstl.setString(12, imgpaths[0]);
			pstl.setString(13, imgpaths[1]);
			pstl.setString(14, imgpaths[2]);
			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 4
	public void insertFour(OtherSearchBean o, String[] imgpaths) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into other_search(id,s_name,s_sex,s_reason,relation,y_name,y_sex,y_age,y_email,y_phone,y_address,photo1,photo2,photo3,photo4)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setInt(1, id);
			pstl.setString(2, o.getS_name());
			pstl.setString(3, o.getS_sex());
			pstl.setString(4, o.getS_reason());
			pstl.setString(5, o.getRelation());
			pstl.setString(6, o.getY_name());
			pstl.setString(7, o.getY_sex());
			pstl.setInt(8, o.getY_age());
			pstl.setString(9, o.getY_email());
			pstl.setString(10, o.getY_phone());
			pstl.setString(11, o.getY_address());
			pstl.setString(12, imgpaths[0]);
			pstl.setString(13, imgpaths[1]);
			pstl.setString(14, imgpaths[2]);
			pstl.setString(15, imgpaths[3]);
			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 5
	public void insertFive(OtherSearchBean o, String[] imgpaths) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into other_search(id,s_name,s_sex,s_reason,relation,y_name,y_sex,y_age,y_email,y_phone,y_address,photo1,photo2,photo3,photo4,photo5)"
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setInt(1, id);
			pstl.setString(2, o.getS_name());
			pstl.setString(3, o.getS_sex());
			pstl.setString(4, o.getS_reason());
			pstl.setString(5, o.getRelation());
			pstl.setString(6, o.getY_name());
			pstl.setString(7, o.getY_sex());
			pstl.setInt(8, o.getY_age());
			pstl.setString(9, o.getY_email());
			pstl.setString(10, o.getY_phone());
			pstl.setString(11, o.getY_address());
			pstl.setString(12, imgpaths[0]);
			pstl.setString(13, imgpaths[1]);
			pstl.setString(14, imgpaths[2]);
			pstl.setString(15, imgpaths[3]);
			pstl.setString(16, imgpaths[4]);
			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
