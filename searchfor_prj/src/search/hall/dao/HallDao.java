package search.hall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import search.entity.Basic_information;
import search.entity.User;
import search.util.DBUtil;

public class HallDao {
	//模糊查询然后返回一个List，其中里面包含这id，图片（图片地址），名字
	public List<Basic_information> findBasic(int type) {
		
		String sqls=" ";
		String middle=type+" ";
		int first=Integer.parseInt(middle.substring(0,1));
		 
		Connection con = null;
		PreparedStatement pstm = null;
		List<Basic_information> basics = new ArrayList<>();
		//根据type选择表名
		if(first==1)
			sqls="select * from search_home where id like '?%';";
		else if(first==2)
			sqls="select * from search_person where id like '%?%';";
		else if(first==3)
			sqls="select * from search_vagrancy where id like '%?%';";
		else if(first==4)
			sqls="select * from other_search where id like '%?%';";
		
		try {
			String sql="select * from search_home where id like ?";
			con = DBUtil.getCon();
//			int ok=new Integer(type);
//			String sql = "select * from search_home";
			pstm = con.prepareStatement(sql);
//			pstm.setInt(1, type);
			pstm.setString(1, type+"%");
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()) {
				String basic_id = rs.getString("id");
				String basic_name = rs.getString("name");
				String basic_sex = rs.getString("gender");
				String basic_photo=rs.getString("photo");
				
				
				Basic_information basic=new Basic_information();
				basic.setId(basic_id);
				basic.setName(basic_name);
				basic.setSex(basic_sex);
				basic.setPhoto(basic_photo);
				basics.add(basic);//将从数据库中查找的所有用户信息放进users列表中
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con);
		}
		return basics;
		
	}
}
