package search.record.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import search.entity.Vagrant;

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
	
	//判断照片数量
	public void judgeImage(Vagrant v,String[]imgpaths){
		int length=imgpaths.length;
		switch(length){
		case 1:
			this.insertVagrantOne(v, imgpaths);
			break;
		case 2:
			this.insertVagrantTwo(v, imgpaths);
			break;
		case 3:
			this.insertVagrantThree(v, imgpaths);
			break;
		case 4:
			this.insertVagrantFour(v, imgpaths);
			break;
		case 5:
			this.insertVagrantFive(v, imgpaths);
			break;
		
		}
	}

	// 5张照片
	public void insertVagrantFive(Vagrant v, String[] imgpaths) {
		Connection conn = null;
		PreparedStatement pstl = null;
		int id = this.getTotalCount() + 1;
		try {
			//conn = new DbUtil().getCon();
			conn=cpds.getConnection();
			String sql = "insert into search_vagrancy(id,name,sex_vagrant,photo1,find_address,begintime_vagrant,targetfamily_vagrant,describe_vagrant,phonenumber,age_vagrant)"
					+ ",photo2,photo3,photo4,photo5)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstl=conn.prepareStatement(sql);
			pstl.setInt(1, id);
			pstl.setString(2, v.getName());
			pstl.setString(3, v.getSex());
			pstl.setString(4, imgpaths[0]);
			pstl.setString(5, v.getFindaddress());
			pstl.setString(6, v.getBegintime());
			pstl.setString(7, v.getTargetfamily());
			pstl.setString(8, v.getDescribe());
			pstl.setString(9, v.getPhonenumber());
			pstl.setString(10, v.getAge());
			pstl.setString(11, imgpaths[1]);
			pstl.setString(12, imgpaths[2]);
			pstl.setString(13, imgpaths[3]);
			pstl.setString(14, imgpaths[4]);
			pstl.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// 4张照片
		public void insertVagrantFour(Vagrant v, String[] imgpaths) {
			Connection conn = null;
			PreparedStatement pstl = null;
			int id = this.getTotalCount() + 1;
			try {
				//conn = new DbUtil().getCon();
				conn=cpds.getConnection();
				String sql = "insert into search_vagrancy(id,name,sex_vagrant,photo1,find_address,begintime_vagrant,targetfamily_vagrant,describe_vagrant,phonenumber,age_vagrant)"
						+ ",photo2,photo3,photo4)" + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstl=conn.prepareStatement(sql);
				pstl.setInt(1, id);
				pstl.setString(2, v.getName());
				pstl.setString(3, v.getSex());
				pstl.setString(4, imgpaths[0]);
				pstl.setString(5, v.getFindaddress());
				pstl.setString(6, v.getBegintime());
				pstl.setString(7, v.getTargetfamily());
				pstl.setString(8, v.getDescribe());
				pstl.setString(9, v.getPhonenumber());
				pstl.setString(10, v.getAge());
				pstl.setString(11, imgpaths[1]);
				pstl.setString(12, imgpaths[2]);
				pstl.setString(13, imgpaths[3]);
			
				pstl.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 3张照片
		public void insertVagrantThree(Vagrant v, String[] imgpaths) {
			Connection conn = null;
			PreparedStatement pstl = null;
			int id = this.getTotalCount() + 1;
			try {
				//conn = new DbUtil().getCon();
				conn=cpds.getConnection();
				String sql = "insert into search_vagrancy(id,name,sex_vagrant,photo1,find_address,begintime_vagrant,targetfamily_vagrant,describe_vagrant,phonenumber,age_vagrant)"
						+ ",photo2,photo3)" + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
				pstl=conn.prepareStatement(sql);
				pstl.setInt(1, id);
				pstl.setString(2, v.getName());
				pstl.setString(3, v.getSex());
				pstl.setString(4, imgpaths[0]);
				pstl.setString(5, v.getFindaddress());
				pstl.setString(6, v.getBegintime());
				pstl.setString(7, v.getTargetfamily());
				pstl.setString(8, v.getDescribe());
				pstl.setString(9, v.getPhonenumber());
				pstl.setString(10, v.getAge());
				pstl.setString(11, imgpaths[1]);
				pstl.setString(12, imgpaths[2]);
				pstl.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 2张照片
		public void insertVagrantTwo(Vagrant v, String[] imgpaths) {
			Connection conn = null;
			PreparedStatement pstl = null;
			int id = this.getTotalCount() + 1;
			try {
				//conn = new DbUtil().getCon();
				conn=cpds.getConnection();
				String sql = "insert into search_vagrancy(id,name,sex_vagrant,photo1,find_address,begintime_vagrant,targetfamily_vagrant,describe_vagrant,phonenumber,age_vagrant)"
						+ ",photo2)" + "values(?,?,?,?,?,?,?,?,?,?,?)";
				pstl=conn.prepareStatement(sql);
				pstl.setInt(1, id);
				pstl.setString(2, v.getName());
				pstl.setString(3, v.getSex());
				pstl.setString(4, imgpaths[0]);
				pstl.setString(5, v.getFindaddress());
				pstl.setString(6, v.getBegintime());
				pstl.setString(7, v.getTargetfamily());
				pstl.setString(8, v.getDescribe());
				pstl.setString(9, v.getPhonenumber());
				pstl.setString(10, v.getAge());
				pstl.setString(11, imgpaths[1]);
				pstl.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 1张照片
		public void insertVagrantOne(Vagrant v, String[] imgpaths) {
			Connection conn = null;
			PreparedStatement pstl = null;
			int id = this.getTotalCount() + 1;
			try {
				//conn = new DbUtil().getCon();
				conn=cpds.getConnection();
				String sql = "insert into search_vagrancy(id,name,sex_vagrant,photo1,find_address,begintime_vagrant,targetfamily_vagrant,describe_vagrant,phonenumber,age_vagrant)"
						 + "values(?,?,?,?,?,?,?,?,?,?)";
				pstl=conn.prepareStatement(sql);
				pstl.setInt(1, id);
				pstl.setString(2, v.getName());
				pstl.setString(3, v.getSex());
				pstl.setString(4, imgpaths[0]);
				pstl.setString(5, v.getFindaddress());
				pstl.setString(6, v.getBegintime());
				pstl.setString(7, v.getTargetfamily());
				pstl.setString(8, v.getDescribe());
				pstl.setString(9, v.getPhonenumber());
				pstl.setString(10, v.getAge());
				pstl.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
		

}
