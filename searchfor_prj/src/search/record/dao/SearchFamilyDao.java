package search.record.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import search.entity.SearchFamilyBean;
public class SearchFamilyDao {

	private static ComboPooledDataSource cpds = new ComboPooledDataSource();

	// 获取总记录数
	public int getTotalCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstl = null;
		try {
			// conn=new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "select * from search_home";
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
	public void judgeImage(SearchFamilyBean sfb, String[] imgpaths) {
		int length = imgpaths.length;
		switch (length) {
		case 1:
			this.insertOne(sfb, imgpaths);
			break;
		case 2:
			this.insertTwo(sfb, imgpaths);
			break;
		case 3:
			this.insertThree(sfb, imgpaths);
			break;
		case 4:
			this.insertFour(sfb, imgpaths);
			break;
		case 5:
			this.insertFive(sfb, imgpaths);
			break;

		}
	}

	// 5张照片
	public void insertFive(SearchFamilyBean sfb, String[] imgpaths) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into search_home(id,l_name,l_sex,l_borndate" + ",l_phone,l_email,l_height,"
					+ "l_missdate,isBlood,isReport"
					+ ",l_native,l_missaddr,l_feature,l_process,l_family,t_familyaddr,t_relationfamily,t_describefamily,"
					+ "photo1,photo2,photo3,photo4,photo5)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setInt(1, id);
			pstl.setString(2, sfb.getL_name());
			pstl.setString(3, sfb.getL_sex());
			pstl.setString(4, sfb.getL_borndate());
			pstl.setString(5, sfb.getL_phone());
			pstl.setString(6, sfb.getL_email());
			pstl.setString(7, sfb.getLheight());
			pstl.setString(8, sfb.getL_missdate());
			pstl.setString(9, sfb.getIsBlood());
			pstl.setString(10, sfb.getIsReport());
			pstl.setString(11, sfb.getL_native());
			pstl.setString(12, sfb.getL_missaddr());
			pstl.setString(13, sfb.getL_fearture());
			pstl.setString(14, sfb.getL_process());
			pstl.setString(15, sfb.getL_family());
			pstl.setString(16, sfb.getT_familyaddr());
			pstl.setString(17, sfb.getT_relationfamily());
			pstl.setString(18, sfb.getT_describefamily());
			pstl.setString(19, imgpaths[0]);
			pstl.setString(20, imgpaths[1]);
			pstl.setString(21, imgpaths[2]);
			pstl.setString(22, imgpaths[3]);
			pstl.setString(23, imgpaths[4]);
			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 4张照片
	public void insertFour(SearchFamilyBean sfb, String[] imgpaths) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into search_home(id,l_name,l_sex,l_borndate" + ",l_phone,l_email,l_height,"
					+ "l_missdate,isBlood,isReport"
					+ ",l_native,l_missaddr,l_feature,l_process,l_family,t_familyaddr,t_relationfamily,t_describefamily,"
					+ "photo1,photo2,photo3,photo4)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setInt(1, id);
			pstl.setString(2, sfb.getL_name());
			pstl.setString(3, sfb.getL_sex());
			pstl.setString(4, sfb.getL_borndate());
			pstl.setString(5, sfb.getL_phone());
			pstl.setString(6, sfb.getL_email());
			pstl.setString(7, sfb.getLheight());
			pstl.setString(8, sfb.getL_missdate());
			pstl.setString(9, sfb.getIsBlood());
			pstl.setString(10, sfb.getIsReport());
			pstl.setString(11, sfb.getL_native());
			pstl.setString(12, sfb.getL_missaddr());
			pstl.setString(13, sfb.getL_fearture());
			pstl.setString(14, sfb.getL_process());
			pstl.setString(15, sfb.getL_family());
			pstl.setString(16, sfb.getT_familyaddr());
			pstl.setString(17, sfb.getT_relationfamily());
			pstl.setString(18, sfb.getT_describefamily());
			pstl.setString(19, imgpaths[0]);
			pstl.setString(20, imgpaths[1]);
			pstl.setString(21, imgpaths[2]);
			pstl.setString(22, imgpaths[3]);
			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 3张照片
	public void insertThree(SearchFamilyBean sfb, String[] imgpaths) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into search_home(id,l_name,l_sex,l_borndate" + ",l_phone,l_email,l_height,"
					+ "l_missdate,isBlood,isReport"
					+ ",l_native,l_missaddr,l_feature,l_process,l_family,t_familyaddr,t_relationfamily,t_describefamily,"
					+ "photo1,photo2,photo3)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setInt(1, id);
			pstl.setString(2, sfb.getL_name());
			pstl.setString(3, sfb.getL_sex());
			pstl.setString(4, sfb.getL_borndate());
			pstl.setString(5, sfb.getL_phone());
			pstl.setString(6, sfb.getL_email());
			pstl.setString(7, sfb.getLheight());
			pstl.setString(8, sfb.getL_missdate());
			pstl.setString(9, sfb.getIsBlood());
			pstl.setString(10, sfb.getIsReport());
			pstl.setString(11, sfb.getL_native());
			pstl.setString(12, sfb.getL_missaddr());
			pstl.setString(13, sfb.getL_fearture());
			pstl.setString(14, sfb.getL_process());
			pstl.setString(15, sfb.getL_family());
			pstl.setString(16, sfb.getT_familyaddr());
			pstl.setString(17, sfb.getT_relationfamily());
			pstl.setString(18, sfb.getT_describefamily());
			pstl.setString(19, imgpaths[0]);
			pstl.setString(20, imgpaths[1]);
			pstl.setString(21, imgpaths[2]);
			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 2张照片
	public void insertTwo(SearchFamilyBean sfb, String[] imgpaths) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into search_home(id,l_name,l_sex,l_borndate" + ",l_phone,l_email,l_height,"
					+ "l_missdate,isBlood,isReport"
					+ ",l_native,l_missaddr,l_feature,l_process,l_family,t_familyaddr,t_relationfamily,t_describefamily,"
					+ "photo1,photo2)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setInt(1, id);
			pstl.setString(2, sfb.getL_name());
			pstl.setString(3, sfb.getL_sex());
			pstl.setString(4, sfb.getL_borndate());
			pstl.setString(5, sfb.getL_phone());
			pstl.setString(6, sfb.getL_email());
			pstl.setString(7, sfb.getLheight());
			pstl.setString(8, sfb.getL_missdate());
			pstl.setString(9, sfb.getIsBlood());
			pstl.setString(10, sfb.getIsReport());
			pstl.setString(11, sfb.getL_native());
			pstl.setString(12, sfb.getL_missaddr());
			pstl.setString(13, sfb.getL_fearture());
			pstl.setString(14, sfb.getL_process());
			pstl.setString(15, sfb.getL_family());
			pstl.setString(16, sfb.getT_familyaddr());
			pstl.setString(17, sfb.getT_relationfamily());
			pstl.setString(18, sfb.getT_describefamily());
			pstl.setString(19, imgpaths[0]);
			pstl.setString(20, imgpaths[1]);
			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 1张照片
	public void insertOne(SearchFamilyBean sfb, String[] imgpaths) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into search_home(id,l_name,l_sex,l_borndate" + ",l_phone,l_email,l_height,"
					+ "l_missdate,isBlood,isReport"
					+ ",l_native,l_missaddr,l_feature,l_process,l_family,t_familyaddr,t_relationfamily,t_describefamily,"
					+ "photo1)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setInt(1, id);
			pstl.setString(2, sfb.getL_name());
			pstl.setString(3, sfb.getL_sex());
			pstl.setString(4, sfb.getL_borndate());
			pstl.setString(5, sfb.getL_phone());
			pstl.setString(6, sfb.getL_email());
			pstl.setString(7, sfb.getLheight());
			pstl.setString(8, sfb.getL_missdate());
			pstl.setString(9, sfb.getIsBlood());
			pstl.setString(10, sfb.getIsReport());
			pstl.setString(11, sfb.getL_native());
			pstl.setString(12, sfb.getL_missaddr());
			pstl.setString(13, sfb.getL_fearture());
			pstl.setString(14, sfb.getL_process());
			pstl.setString(15, sfb.getL_family());
			pstl.setString(16, sfb.getT_familyaddr());
			pstl.setString(17, sfb.getT_relationfamily());
			pstl.setString(18, sfb.getT_describefamily());
			pstl.setString(19, imgpaths[0]);
			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
