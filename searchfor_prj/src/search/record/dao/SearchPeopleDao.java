package search.record.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import search.entity.SearchPeopleBean;
import search.util.DBUtil;

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
	//获取我的发布寻人信息
			public List<SearchPeopleBean> findSearchPeopleBeans(int user_id) {
				Connection con = null;
				PreparedStatement pstm = null;
				List<SearchPeopleBean> SearchPeopleBeans = new ArrayList<>();
				
				try {
					con = cpds.getConnection();
					String sql = "select * from search_person where user_id ="+user_id;
					pstm = con.prepareStatement(sql);
					ResultSet rs = pstm.executeQuery();
					while(rs.next()) {
						SearchPeopleBean sp=new SearchPeopleBean(rs.getString("m_name"), rs.getString("m_sex") ,
								rs.getString("m_borndate"), rs.getString("height") , rs.getString("m_missdate") , 
								rs.getString("isBlood"), rs.getString("isReport") ,rs.getString("m_native") , rs.getString("m_missaddr") , 
								rs.getString("m_feature") , rs.getString("m_process") , rs.getString("m_family") , rs.getString("y_name") ,
								rs.getString("y_phone") , rs.getString("y_email") , rs.getString("y_address"), rs.getString("y_relation") );
	

						 SearchPeopleBeans.add(sp);//将从数据库中查找的所有用户信息放进 SearchPeopleBeans列表中
					}
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					DBUtil.close(con);
				}
				return SearchPeopleBeans;
				
			}
	// 判断传入图片的数量
	public void judgeImage(SearchPeopleBean spb, String[] imgpaths,int user_id) {
		int length = imgpaths.length;
		switch (length) {
		case 1:
			this.insertOne(spb, imgpaths ,user_id);
			break;
		case 2:
			this.insertTwo(spb, imgpaths,user_id);
			break;
		case 3:
			this.insertThree(spb, imgpaths,user_id);
			break;
		case 4:
			this.insertFour(spb, imgpaths,user_id);
			break;
		case 5:
			this.insertFive(spb, imgpaths,user_id);
			break;

		}
	}

	// 5张照片
	public void insertFive(SearchPeopleBean spb, String[] imgpaths,int user_id) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into search_person(m_name,m_sex,m_borndate,height,m_missdate,"
					+ "isBlood,isReport,m_native,m_missaddr,m_feature,m_process,m_family,y_name,y_phone,y_email,y_address,y_relation,"
					+ "photo1,photo2,photo3,photo4,photo5,user_id)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setString(1, spb.getM_name());
			pstl.setString(2, spb.getM_sex());
			pstl.setString(3, spb.getM_borndate());
			pstl.setString(4, spb.getHeight());
			pstl.setString(5, spb.getM_missdate());
			pstl.setString(6, spb.getIsBlood());
			pstl.setString(7, spb.getIsReport());
			pstl.setString(8, spb.getM_native());
			pstl.setString(9, spb.getM_missadd());
			pstl.setString(10, spb.getM_fearture());
			pstl.setString(11, spb.getM_process());
			pstl.setString(12, spb.getM_family());
			pstl.setString(13, spb.getY_name());
			pstl.setString(14, spb.getY_phone());
			pstl.setString(15, spb.getY_email());
			pstl.setString(16, spb.getY_address());
			pstl.setString(17, spb.getY_relation());
			pstl.setString(18, imgpaths[0]);
			pstl.setString(19, imgpaths[1]);
			pstl.setString(20, imgpaths[2]);
			pstl.setString(21, imgpaths[3]);
			pstl.setString(22, imgpaths[4]);
			pstl.setInt(23, user_id);
			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 4张照片
	public void insertFour(SearchPeopleBean spb, String[] imgpaths,int user_id) {
		Connection conn = null;
		PreparedStatement pstl = null;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into search_person(m_name,m_sex,m_borndate,height,m_missdate,"
					+ "isBlood,isReport,m_native,m_missaddr,m_feature,m_process,m_family,y_name,y_phone,y_email,y_address,y_relation,"
					+ "photo1,photo2,photo3,photo4,user_id)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setString(1, spb.getM_name());
			pstl.setString(2, spb.getM_sex());
			pstl.setString(3, spb.getM_borndate());
			pstl.setString(4, spb.getHeight());
			pstl.setString(5, spb.getM_missdate());
			pstl.setString(6, spb.getIsBlood());
			pstl.setString(7, spb.getIsReport());
			pstl.setString(8, spb.getM_native());
			pstl.setString(9, spb.getM_missadd());
			pstl.setString(10, spb.getM_fearture());
			pstl.setString(11, spb.getM_process());
			pstl.setString(12, spb.getM_family());
			pstl.setString(13, spb.getY_name());
			pstl.setString(14, spb.getY_phone());
			pstl.setString(15, spb.getY_email());
			pstl.setString(16, spb.getY_address());
			pstl.setString(17, spb.getY_relation());
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
	public void insertThree(SearchPeopleBean spb, String[] imgpaths,int user_id) {
		Connection conn = null;
		PreparedStatement pstl = null;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into search_person(m_name,m_sex,m_borndate,height,m_missdate,"
					+ "isBlood,isReport,m_native,m_missaddr,m_feature,m_process,m_family,y_name,y_phone,y_email,y_address,y_relation,"
					+ "photo1,photo2,photo3,user_id)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setString(1, spb.getM_name());
			pstl.setString(2, spb.getM_sex());
			pstl.setString(3, spb.getM_borndate());
			pstl.setString(4, spb.getHeight());
			pstl.setString(5, spb.getM_missdate());
			pstl.setString(6, spb.getIsBlood());
			pstl.setString(7, spb.getIsReport());
			pstl.setString(8, spb.getM_native());
			pstl.setString(9, spb.getM_missadd());
			pstl.setString(10, spb.getM_fearture());
			pstl.setString(11, spb.getM_process());
			pstl.setString(12, spb.getM_family());
			pstl.setString(13, spb.getY_name());
			pstl.setString(14, spb.getY_phone());
			pstl.setString(15, spb.getY_email());
			pstl.setString(16, spb.getY_address());
			pstl.setString(17, spb.getY_relation());
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
	public void insertTwo(SearchPeopleBean spb, String[] imgpaths,int user_id) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into search_person(m_name,m_sex,m_borndate,height,m_missdate,"
					+ "isBlood,isReport,m_native,m_missaddr,m_feature,m_process,m_family,y_name,y_phone,y_email,y_address,y_relation,"
					+ "photo1,photo2,user_id)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setString(1, spb.getM_name());
			pstl.setString(2, spb.getM_sex());
			pstl.setString(3, spb.getM_borndate());
			pstl.setString(4, spb.getHeight());
			pstl.setString(5, spb.getM_missdate());
			pstl.setString(6, spb.getIsBlood());
			pstl.setString(7, spb.getIsReport());
			pstl.setString(8, spb.getM_native());
			pstl.setString(9, spb.getM_missadd());
			pstl.setString(10, spb.getM_fearture());
			pstl.setString(11, spb.getM_process());
			pstl.setString(12, spb.getM_family());
			pstl.setString(13, spb.getY_name());
			pstl.setString(14, spb.getY_phone());
			pstl.setString(15, spb.getY_email());
			pstl.setString(16, spb.getY_address());
			pstl.setString(17, spb.getY_relation());
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
	public void insertOne(SearchPeopleBean spb, String[] imgpaths,int user_id) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			// conn = new DbUtil().getCon();
			conn = cpds.getConnection();
			String sql = "insert into search_person(m_name,m_sex,m_borndate,height,m_missdate,"
					+ "isBlood,isReport,m_native,m_missaddr,m_feature,m_process,m_family,y_name,y_phone,y_email,y_address,y_relation,"
					+ "photo1,user_id)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl = conn.prepareStatement(sql);
			pstl.setString(1, spb.getM_name());
			pstl.setString(2, spb.getM_sex());
			pstl.setString(3, spb.getM_borndate());
			pstl.setString(4, spb.getHeight());
			pstl.setString(5, spb.getM_missdate());
			pstl.setString(6, spb.getIsBlood());
			pstl.setString(7, spb.getIsReport());
			pstl.setString(8, spb.getM_native());
			pstl.setString(9, spb.getM_missadd());
			pstl.setString(10, spb.getM_fearture());
			pstl.setString(11, spb.getM_process());
			pstl.setString(12, spb.getM_family());
			pstl.setString(13, spb.getY_name());
			pstl.setString(14, spb.getY_phone());
			pstl.setString(15, spb.getY_email());
			pstl.setString(16, spb.getY_address());
			pstl.setString(17, spb.getY_relation());
			pstl.setString(18, imgpaths[0]);
			pstl.setInt(19, user_id);

			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
