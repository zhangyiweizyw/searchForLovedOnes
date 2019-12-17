package search.record.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import search.entity.Vagrant;
import search.util.DBUtil;

public class VagrantDao {
	
	private static ComboPooledDataSource cpds = new ComboPooledDataSource();

	// 获得总记录数
	public int getTotalCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstl = null;
		try {
			//conn=new DbUtil().getCon();
			conn=cpds.getConnection();
			String sql = "select * from search_vagrancy";
			pstl=conn.prepareStatement(sql);
			ResultSet rs=pstl.executeQuery();
			while(rs.next()){
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("总记录数"+count);
		return count;
	}
	//获取我的发布寻人信息
	public List<Vagrant> findVagrants(int user_id) {
		Connection con = null;
		PreparedStatement pstm = null;
		List<Vagrant> Vagrants = new ArrayList<>();
		
		try {
			con = cpds.getConnection();
			String sql = "select * from search_vagrancy where user_id ="+user_id;
			pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				Vagrant sp=new Vagrant(rs.getString("name"), rs.getString("sex_vagrant") ,
						rs.getString("age_vagrant"), rs.getString("find_address") , rs.getString("begintime_vagrant") , 
						rs.getString("targetfamily_vagrant"), rs.getString("describe_vagrant") ,rs.getString("phonenumber"));
				

				Vagrants.add(sp);//将从数据库中查找的所有用户信息放进 SearchPeopleBeans列表中
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con);
		}
		return Vagrants;
		
	}
	//判断照片数量
	public void judgeImage(Vagrant v,String[]imgpaths,int user_id){
		int length=imgpaths.length;
		switch(length){
		case 1:
			this.insertVagrantOne(v, imgpaths,user_id);
			break;
		case 2:
			this.insertVagrantTwo(v, imgpaths,user_id);
			break;
		case 3:
			this.insertVagrantThree(v, imgpaths,user_id);
			break;
		case 4:
			this.insertVagrantFour(v, imgpaths,user_id);
			break;
		case 5:
			this.insertVagrantFive(v, imgpaths,user_id);
			break;
		
		}
	}

	// 5张照片
	public void insertVagrantFive(Vagrant v, String[] imgpaths,int user_id) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			//conn = new DbUtil().getCon();
			conn=cpds.getConnection();
			String sql = "insert into search_vagrancy(name,sex_vagrant,photo1,find_address,begintime_vagrant,targetfamily_vagrant,describe_vagrant,phonenumber,age_vagrant)"
					+ ",photo2,photo3,photo4,photo5,user_id)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl=conn.prepareStatement(sql);
			pstl.setString(1, v.getName());
			pstl.setString(2, v.getSex());
			pstl.setString(3, imgpaths[0]);
			pstl.setString(4, v.getFindaddress());
			pstl.setString(5, v.getBegintime());
			pstl.setString(6, v.getTargetfamily());
			pstl.setString(7, v.getDescribe());
			pstl.setString(8, v.getPhonenumber());
			pstl.setString(9, v.getAge());
			pstl.setString(10, imgpaths[1]);
			pstl.setString(11, imgpaths[2]);
			pstl.setString(12, imgpaths[3]);
			pstl.setString(13, imgpaths[4]);
			pstl.setInt(14, user_id);
			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 4张照片
		public void insertVagrantFour(Vagrant v, String[] imgpaths,int user_id) {
			Connection conn = null;
			PreparedStatement pstl = null;
			int id = this.getTotalCount() + 1;
			try {
				//conn = new DbUtil().getCon();
				conn=cpds.getConnection();
				String sql = "insert into search_vagrancy(name,sex_vagrant,photo1,find_address,begintime_vagrant,targetfamily_vagrant,describe_vagrant,phonenumber,age_vagrant)"
						+ ",photo2,photo3,photo4,user_id)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstl=conn.prepareStatement(sql);
				
				pstl.setString(1, v.getName());
				pstl.setString(2, v.getSex());
				pstl.setString(3, imgpaths[0]);
				pstl.setString(4, v.getFindaddress());
				pstl.setString(5, v.getBegintime());
				pstl.setString(6, v.getTargetfamily());
				pstl.setString(7, v.getDescribe());
				pstl.setString(8, v.getPhonenumber());
				pstl.setString(9, v.getAge());
				pstl.setString(10, imgpaths[1]);
				pstl.setString(11, imgpaths[2]);
				pstl.setString(12, imgpaths[3]);
				pstl.setInt(13, user_id);
			
				pstl.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 3张照片
		public void insertVagrantThree(Vagrant v, String[] imgpaths,int user_id) {
			Connection conn = null;
			PreparedStatement pstl = null;
			int id = this.getTotalCount() + 1;
			try {
				//conn = new DbUtil().getCon();
				conn=cpds.getConnection();
				String sql = "insert into search_vagrancy(name,sex_vagrant,photo1,find_address,begintime_vagrant,targetfamily_vagrant,describe_vagrant,phonenumber,age_vagrant)"
						+ ",photo2,photo3,user_id)" + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
				pstl=conn.prepareStatement(sql);
				pstl.setString(1, v.getName());
				pstl.setString(2, v.getSex());
				pstl.setString(3, imgpaths[0]);
				pstl.setString(4, v.getFindaddress());
				pstl.setString(5, v.getBegintime());
				pstl.setString(6, v.getTargetfamily());
				pstl.setString(7, v.getDescribe());
				pstl.setString(8, v.getPhonenumber());
				pstl.setString(9, v.getAge());
				pstl.setString(10, imgpaths[1]);
				pstl.setString(11, imgpaths[2]);
				pstl.setInt(12, user_id);
				pstl.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 2张照片
		public void insertVagrantTwo(Vagrant v, String[] imgpaths,int user_id) {
			Connection conn = null;
			PreparedStatement pstl = null;
			int id = this.getTotalCount() + 1;
			try {
				//conn = new DbUtil().getCon();
				conn=cpds.getConnection();
				String sql = "insert into search_vagrancy(name,sex_vagrant,photo1,find_address,begintime_vagrant,targetfamily_vagrant,describe_vagrant,phonenumber,age_vagrant)"
						+ ",photo2,user_id)" + "values(?,?,?,?,?,?,?,?,?,?,?)";
				pstl=conn.prepareStatement(sql);
				pstl.setString(1, v.getName());
				pstl.setString(2, v.getSex());
				pstl.setString(3, imgpaths[0]);
				pstl.setString(4, v.getFindaddress());
				pstl.setString(5, v.getBegintime());
				pstl.setString(6, v.getTargetfamily());
				pstl.setString(7, v.getDescribe());
				pstl.setString(8, v.getPhonenumber());
				pstl.setString(9, v.getAge());
				pstl.setString(10, imgpaths[1]);
				pstl.setInt(11, user_id);
				pstl.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 1张照片
		public void insertVagrantOne(Vagrant v, String[] imgpaths,int user_id) {
			Connection conn = null;
			PreparedStatement pstl = null;
			int id = this.getTotalCount() + 1;
			try {
				//conn = new DbUtil().getCon();
				conn=cpds.getConnection();
				String sql = "insert into search_vagrancy(name,sex_vagrant,photo1,find_address,begintime_vagrant,targetfamily_vagrant,describe_vagrant,phonenumber,age_vagrant,user_id)"
						 + "values(?,?,?,?,?,?,?,?,?,?)";
				pstl=conn.prepareStatement(sql);
				pstl.setString(1, v.getName());
				pstl.setString(2, v.getSex());
				pstl.setString(3, imgpaths[0]);
				pstl.setString(4, v.getFindaddress());
				pstl.setString(5, v.getBegintime());
				pstl.setString(6, v.getTargetfamily());
				pstl.setString(7, v.getDescribe());
				pstl.setString(8, v.getPhonenumber());
				pstl.setString(9, v.getAge());
				pstl.setInt(10, user_id);
				pstl.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
		

}
