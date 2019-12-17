package search.record.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import search.entity.SearchFamilyBean;
import search.util.DBUtil;
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
	//获取我的发布寻人信息
	public List<SearchFamilyBean> findSearchFamilyBeans(int user_id) {
		Connection con = null;
		PreparedStatement pstm = null;
		List<SearchFamilyBean> SearchFamilyBeans = new ArrayList<>();
		
		try {
			con = cpds.getConnection();
			String sql = "select * from search_home where user_id ="+user_id;
			pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				SearchFamilyBean sp=new SearchFamilyBean(rs.getString("l_name"), rs.getString("l_sex") ,
						rs.getString("l_borndate"), rs.getString("l_phone") , rs.getString("l_email") , 
						rs.getString("l_height"), rs.getString("l_missdate") ,rs.getString("isBlood") , rs.getString("isReport") , 
						rs.getString("l_native") , rs.getString("l_missaddr") , rs.getString("l_feature") , rs.getString("l_process") ,
						rs.getString("l_family") , rs.getString("t_familyaddr") , rs.getString("t_relationfamily"), rs.getString("t_describefamily") );
				

				SearchFamilyBeans.add(sp);//将从数据库中查找的所有用户信息放进 SearchPeopleBeans列表中
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con);
		}
		return SearchFamilyBeans;
		
	}
	// 判断图片数量
	public void judgeImage(SearchFamilyBean sfb, String[] imgpaths,int user_id) {
		int length = imgpaths.length;
		switch (length) {
		case 1:
			this.insertOne(sfb, imgpaths,user_id);
			break;
		case 2:
			this.insertTwo(sfb, imgpaths,user_id);
			break;
		case 3:
			this.insertThree(sfb, imgpaths,user_id);
			break;
		case 4:
			this.insertFour(sfb, imgpaths,user_id);
			break;
		case 5:
			this.insertFive(sfb, imgpaths,user_id);
			break;

		}
	}

	// 5张照片
	public void insertFive(SearchFamilyBean sfb, String[] imgpaths,int user_id) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into search_home(l_name,l_sex,l_borndate" + ",l_phone,l_email,l_height,"
					+ "l_missdate,isBlood,isReport"
					+ ",l_native,l_missaddr,l_feature,l_process,l_family,t_familyaddr,t_relationfamily,t_describefamily,"
					+ "photo1,photo2,photo3,photo4,photo5,user_id)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setString(1, sfb.getL_name());
			pstl.setString(2, sfb.getL_sex());
			pstl.setString(3, sfb.getL_borndate());
			pstl.setString(4, sfb.getL_phone());
			pstl.setString(5, sfb.getL_email());
			pstl.setString(6, sfb.getLheight());
			pstl.setString(7, sfb.getL_missdate());
			pstl.setString(8, sfb.getIsBlood());
			pstl.setString(9, sfb.getIsReport());
			pstl.setString(10, sfb.getL_native());
			pstl.setString(11, sfb.getL_missaddr());
			pstl.setString(12, sfb.getL_fearture());
			pstl.setString(13, sfb.getL_process());
			pstl.setString(14, sfb.getL_family());
			pstl.setString(15, sfb.getT_familyaddr());
			pstl.setString(16, sfb.getT_relationfamily());
			pstl.setString(17, sfb.getT_describefamily());
			pstl.setString(18, imgpaths[0]);
			pstl.setString(19, imgpaths[1]);
			pstl.setString(20, imgpaths[2]);
			pstl.setString(21, imgpaths[3]);
			pstl.setString(22, imgpaths[4]);
			pstl.setInt(23,user_id);
			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 4张照片
	public void insertFour(SearchFamilyBean sfb, String[] imgpaths,int user_id) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into search_home(l_name,l_sex,l_borndate" + ",l_phone,l_email,l_height,"
					+ "l_missdate,isBlood,isReport"
					+ ",l_native,l_missaddr,l_feature,l_process,l_family,t_familyaddr,t_relationfamily,t_describefamily,"
					+ "photo1,photo2,photo3,photo4,user_id)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setString(1, sfb.getL_name());
			pstl.setString(2, sfb.getL_sex());
			pstl.setString(3, sfb.getL_borndate());
			pstl.setString(4, sfb.getL_phone());
			pstl.setString(5, sfb.getL_email());
			pstl.setString(6, sfb.getLheight());
			pstl.setString(7, sfb.getL_missdate());
			pstl.setString(8, sfb.getIsBlood());
			pstl.setString(9, sfb.getIsReport());
			pstl.setString(10, sfb.getL_native());
			pstl.setString(11, sfb.getL_missaddr());
			pstl.setString(12, sfb.getL_fearture());
			pstl.setString(13, sfb.getL_process());
			pstl.setString(14, sfb.getL_family());
			pstl.setString(15, sfb.getT_familyaddr());
			pstl.setString(16, sfb.getT_relationfamily());
			pstl.setString(17, sfb.getT_describefamily());
			pstl.setString(18, imgpaths[0]);
			pstl.setString(19, imgpaths[1]);
			pstl.setString(20, imgpaths[2]);
			pstl.setString(21, imgpaths[3]);
			pstl.setInt(22, user_id);
			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 3张照片
	public void insertThree(SearchFamilyBean sfb, String[] imgpaths,int user_id) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into search_home(l_name,l_sex,l_borndate" + ",l_phone,l_email,l_height,"
					+ "l_missdate,isBlood,isReport"
					+ ",l_native,l_missaddr,l_feature,l_process,l_family,t_familyaddr,t_relationfamily,t_describefamily,"
					+ "photo1,photo2,photo3,user_id)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setString(1, sfb.getL_name());
			pstl.setString(2, sfb.getL_sex());
			pstl.setString(3, sfb.getL_borndate());
			pstl.setString(4, sfb.getL_phone());
			pstl.setString(5, sfb.getL_email());
			pstl.setString(6, sfb.getLheight());
			pstl.setString(7, sfb.getL_missdate());
			pstl.setString(8, sfb.getIsBlood());
			pstl.setString(9, sfb.getIsReport());
			pstl.setString(10, sfb.getL_native());
			pstl.setString(11, sfb.getL_missaddr());
			pstl.setString(12, sfb.getL_fearture());
			pstl.setString(13, sfb.getL_process());
			pstl.setString(14, sfb.getL_family());
			pstl.setString(15, sfb.getT_familyaddr());
			pstl.setString(16, sfb.getT_relationfamily());
			pstl.setString(17, sfb.getT_describefamily());
			pstl.setString(18, imgpaths[0]);
			pstl.setString(19, imgpaths[1]);
			pstl.setString(20, imgpaths[2]);
			pstl.setInt(21, user_id);
			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 2张照片
	public void insertTwo(SearchFamilyBean sfb, String[] imgpaths,int user_id) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into search_home(l_name,l_sex,l_borndate" + ",l_phone,l_email,l_height,"
					+ "l_missdate,isBlood,isReport"
					+ ",l_native,l_missaddr,l_feature,l_process,l_family,t_familyaddr,t_relationfamily,t_describefamily,"
					+ "photo1,photo2,user_id)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setString(1, sfb.getL_name());
			pstl.setString(2, sfb.getL_sex());
			pstl.setString(3, sfb.getL_borndate());
			pstl.setString(4, sfb.getL_phone());
			pstl.setString(5, sfb.getL_email());
			pstl.setString(6, sfb.getLheight());
			pstl.setString(7, sfb.getL_missdate());
			pstl.setString(8, sfb.getIsBlood());
			pstl.setString(9, sfb.getIsReport());
			pstl.setString(10, sfb.getL_native());
			pstl.setString(11, sfb.getL_missaddr());
			pstl.setString(12, sfb.getL_fearture());
			pstl.setString(13, sfb.getL_process());
			pstl.setString(14, sfb.getL_family());
			pstl.setString(15, sfb.getT_familyaddr());
			pstl.setString(16, sfb.getT_relationfamily());
			pstl.setString(17, sfb.getT_describefamily());
			pstl.setString(18, imgpaths[0]);
			pstl.setString(19, imgpaths[1]);
			pstl.setInt(20, user_id);
			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 1张照片
	public void insertOne(SearchFamilyBean sfb, String[] imgpaths,int user_id) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into search_home(l_name,l_sex,l_borndate" + ",l_phone,l_email,l_height,"
					+ "l_missdate,isBlood,isReport"
					+ ",l_native,l_missaddr,l_feature,l_process,l_family,t_familyaddr,t_relationfamily,t_describefamily,"
					+ "photo1,user_id)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setString(1, sfb.getL_name());
			pstl.setString(2, sfb.getL_sex());
			pstl.setString(3, sfb.getL_borndate());
			pstl.setString(4, sfb.getL_phone());
			pstl.setString(5, sfb.getL_email());
			pstl.setString(6, sfb.getLheight());
			pstl.setString(7, sfb.getL_missdate());
			pstl.setString(8, sfb.getIsBlood());
			pstl.setString(9, sfb.getIsReport());
			pstl.setString(10, sfb.getL_native());
			pstl.setString(11, sfb.getL_missaddr());
			pstl.setString(12, sfb.getL_fearture());
			pstl.setString(13, sfb.getL_process());
			pstl.setString(14, sfb.getL_family());
			pstl.setString(15, sfb.getT_familyaddr());
			pstl.setString(16, sfb.getT_relationfamily());
			pstl.setString(17, sfb.getT_describefamily());
			pstl.setString(18, imgpaths[0]);
			pstl.setInt(19, user_id);
			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
