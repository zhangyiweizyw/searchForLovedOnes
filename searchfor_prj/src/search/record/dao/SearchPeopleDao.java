package search.record.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import search.entity.SearchPeopleBean;

public class SearchPeopleDao {

	private static ComboPooledDataSource cpds = new ComboPooledDataSource();

	// 获得总计录数
	public int getTotalCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstl = null;
		try {
			// conn=new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "select * from search_person";
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

	// 判断传入图片的数量
	public void judgeImage(SearchPeopleBean spb, String[] imgpaths) {
		int length = imgpaths.length;
		switch (length) {
		case 1:
			this.insertOne(spb, imgpaths);
			break;
		case 2:
			this.insertTwo(spb, imgpaths);
			break;
		case 3:
			this.insertThree(spb, imgpaths);
			break;
		case 4:
			this.insertFour(spb, imgpaths);
			break;
		case 5:
			this.insertFive(spb, imgpaths);
			break;

		}
	}

	// 5张照片
	public void insertFive(SearchPeopleBean spb, String[] imgpaths) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into search_person(id,m_name,m_sex,m_borndate,height,m_missdate,"
					+ "isBlood,isReport,m_native,m_missaddr,m_feature,m_process,m_family,y_name,y_phone,y_email,y_address,y_relation,"
					+ "photo1,photo2,photo3,photo4,photo5)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setInt(1, id);
			pstl.setString(2, spb.getM_name());
			pstl.setString(3, spb.getM_sex());
			pstl.setString(4, spb.getM_borndate());
			pstl.setString(5, spb.getHeight());
			pstl.setString(6, spb.getM_missdate());
			pstl.setString(7, spb.getIsBlood());
			pstl.setString(8, spb.getIsReport());
			pstl.setString(9, spb.getM_native());
			pstl.setString(10, spb.getM_missadd());
			pstl.setString(11, spb.getM_fearture());
			pstl.setString(12, spb.getM_process());
			pstl.setString(13, spb.getM_family());
			pstl.setString(14, spb.getY_name());
			pstl.setString(15, spb.getY_phone());
			pstl.setString(16, spb.getY_email());
			pstl.setString(17, spb.getY_address());
			pstl.setString(18, spb.getY_relation());
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
	public void insertFour(SearchPeopleBean spb, String[] imgpaths) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into search_person(id,m_name,m_sex,m_borndate,height,m_missdate,"
					+ "isBlood,isReport,m_native,m_missaddr,m_feature,m_process,m_family,y_name,y_phone,y_email,y_address,y_relation,"
					+ "photo1,photo2,photo3,photo4)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setInt(1, id);
			pstl.setString(2, spb.getM_name());
			pstl.setString(3, spb.getM_sex());
			pstl.setString(4, spb.getM_borndate());
			pstl.setString(5, spb.getHeight());
			pstl.setString(6, spb.getM_missdate());
			pstl.setString(7, spb.getIsBlood());
			pstl.setString(8, spb.getIsReport());
			pstl.setString(9, spb.getM_native());
			pstl.setString(10, spb.getM_missadd());
			pstl.setString(11, spb.getM_fearture());
			pstl.setString(12, spb.getM_process());
			pstl.setString(13, spb.getM_family());
			pstl.setString(14, spb.getY_name());
			pstl.setString(15, spb.getY_phone());
			pstl.setString(16, spb.getY_email());
			pstl.setString(17, spb.getY_address());
			pstl.setString(18, spb.getY_relation());
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
	public void insertThree(SearchPeopleBean spb, String[] imgpaths) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into search_person(id,m_name,m_sex,m_borndate,height,m_missdate,"
					+ "isBlood,isReport,m_native,m_missaddr,m_feature,m_process,m_family,y_name,y_phone,y_email,y_address,y_relation,"
					+ "photo1,photo2,photo3)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setInt(1, id);
			pstl.setString(2, spb.getM_name());
			pstl.setString(3, spb.getM_sex());
			pstl.setString(4, spb.getM_borndate());
			pstl.setString(5, spb.getHeight());
			pstl.setString(6, spb.getM_missdate());
			pstl.setString(7, spb.getIsBlood());
			pstl.setString(8, spb.getIsReport());
			pstl.setString(9, spb.getM_native());
			pstl.setString(10, spb.getM_missadd());
			pstl.setString(11, spb.getM_fearture());
			pstl.setString(12, spb.getM_process());
			pstl.setString(13, spb.getM_family());
			pstl.setString(14, spb.getY_name());
			pstl.setString(15, spb.getY_phone());
			pstl.setString(16, spb.getY_email());
			pstl.setString(17, spb.getY_address());
			pstl.setString(18, spb.getY_relation());
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
	public void insertTwo(SearchPeopleBean spb, String[] imgpaths) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into search_person(id,m_name,m_sex,m_borndate,height,m_missdate,"
					+ "isBlood,isReport,m_native,m_missaddr,m_feature,m_process,m_family,y_name,y_phone,y_email,y_address,y_relation,"
					+ "photo1,photo2)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setInt(1, id);
			pstl.setString(2, spb.getM_name());
			pstl.setString(3, spb.getM_sex());
			pstl.setString(4, spb.getM_borndate());
			pstl.setString(5, spb.getHeight());
			pstl.setString(6, spb.getM_missdate());
			pstl.setString(7, spb.getIsBlood());
			pstl.setString(8, spb.getIsReport());
			pstl.setString(9, spb.getM_native());
			pstl.setString(10, spb.getM_missadd());
			pstl.setString(11, spb.getM_fearture());
			pstl.setString(12, spb.getM_process());
			pstl.setString(13, spb.getM_family());
			pstl.setString(14, spb.getY_name());
			pstl.setString(15, spb.getY_phone());
			pstl.setString(16, spb.getY_email());
			pstl.setString(17, spb.getY_address());
			pstl.setString(18, spb.getY_relation());
			pstl.setString(19, imgpaths[0]);
			pstl.setString(20, imgpaths[1]);
			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 1张照片
	public void insertOne(SearchPeopleBean spb, String[] imgpaths) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into search_person(id,m_name,m_sex,m_borndate,height,m_missdate,"
					+ "isBlood,isReport,m_native,m_missaddr,m_feature,m_process,m_family,y_name,y_phone,y_email,y_address,y_relation,"
					+ "photo1)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setInt(1, id);
			pstl.setString(2, spb.getM_name());
			pstl.setString(3, spb.getM_sex());
			pstl.setString(4, spb.getM_borndate());
			pstl.setString(5, spb.getHeight());
			pstl.setString(6, spb.getM_missdate());
			pstl.setString(7, spb.getIsBlood());
			pstl.setString(8, spb.getIsReport());
			pstl.setString(9, spb.getM_native());
			pstl.setString(10, spb.getM_missadd());
			pstl.setString(11, spb.getM_fearture());
			pstl.setString(12, spb.getM_process());
			pstl.setString(13, spb.getM_family());
			pstl.setString(14, spb.getY_name());
			pstl.setString(15, spb.getY_phone());
			pstl.setString(16, spb.getY_email());
			pstl.setString(17, spb.getY_address());
			pstl.setString(18, spb.getY_relation());
			pstl.setString(19, imgpaths[0]);

			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
