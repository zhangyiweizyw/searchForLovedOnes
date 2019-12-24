package search.hall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import search.entity.Basic_information;
import search.entity.PageText;
import search.entity.User;
import search.util.DBUtil;

public class HallDao {
	//模糊查询然后返回一个List，其中里面包含这id，图片（图片地址），名字
	/**
	 * 根据id进行搜索
	 * @param type
	 * @return return 一个list
	 */
	public List<Basic_information> findBasicByID(int type) {
		String sqls=" ";
		String table_name;
		String middle=type+"";
		int first=Integer.parseInt(middle.substring(0,1));

		Connection con = null;
		PreparedStatement pstm = null;
		List<Basic_information> basics = new ArrayList<>();
		//根据type选择表名
		if(first==1)
			sqls="select * from search_home where id like ?";
		else if(first==2)
			sqls="select * from search_person where id like ?";
		else if(first==3)
			sqls="select * from search_vagrancy where id like ?";
		else if(first==4)
			sqls="select * from other_search where id like ?";

		try {
			//			String sql="select * from ? where id ? ?";
			con = DBUtil.getCon();

			String typelenth=type+"";
			int length=typelenth.length();
			System.out.println("获取到的字符串长度是"+length);
			//			int ok=new Integer(type);
			//			String sql = "select * from search_home";

			pstm = con.prepareStatement(sqls);
			//			pstm.setInt(1, type);

			//判断一下type的长度（最小为1，最大为4）,如果长度为1或小于4的话（暂定id最大长度为4）的话说明是单选框事件，应该用模糊查询
			//如果长度为4（暂定id最大长度为4）的话，就采用精确查询

			//符合长度要求可以检索
			if(length<4&&length>0) {//启用模糊查询
				System.out.println("启用模糊查询");
				pstm.setString(1, type+"%");
			}
			else if(4==length){//启用精确查询
				System.out.println("启用精确查询");
				pstm.setInt(1, type);
			}


			ResultSet rs = pstm.executeQuery();

			while(rs.next()) {
				//				String basic_id = rs.getString("id");
				//				String basic_name = rs.getString("name");
				//				String basic_sex = rs.getString("gender");//性别QAQ
				//				String basic_photo=rs.getString("photo");
				String basic_id = rs.getString(1);
				String basic_name = rs.getString(2);
				String basic_sex = rs.getString(3);//性别QAQ
				//				String basic_photo=rs.getString("photo");
				//合并时需要改成
				String basic_photo=rs.getString("photo1");


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
	/**
	 * 根据人名进行搜索
	 * @param name
	 * @return return 一个数组
	 */
	public List<Basic_information> findBasicByName(String name) {
		Connection con = null;
		PreparedStatement pstm = null;
		List<Basic_information> basics = new ArrayList<>();
		try {
			String sql=" ";
			int num=4;
			con = DBUtil.getCon();
			while(num>0) {
				if(4==num)
				{
					sql="select * from search_home where l_name like ?";
					//合并时需要改成l_name
				}	
				else if(3==num)
				{
					sql="select * from search_person where m_name like ?";
					//合并时需要改成m_name
				}
				else if(2==num)
				{
					sql="select * from search_vagrancy where name like ?";

				}
				else if(1==num)
				{
					sql="select * from other_search where s_name like ?";
					//合并时需要改成s_name
				}
				pstm = con.prepareStatement(sql);
				pstm.setString(1, "%"+name+"%");
				ResultSet rs = pstm.executeQuery();
				while(rs.next()) {
					String basic_id = rs.getString(1);
					String basic_name = rs.getString(2);
					String basic_sex = rs.getString(3);//性别QAQ
					String basic_photo=rs.getString("photo1");
					//合并时需要改成photo1


					Basic_information basic=new Basic_information();
					basic.setId(basic_id);
					basic.setName(basic_name);
					basic.setSex(basic_sex);
					basic.setPhoto(basic_photo);
					basics.add(basic);//将从数据库中查找的所有用户信息放进users列表中
				}
				--num;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con);
		}
		return basics;

	}
	/**
	 * 从数据库中任取一些数据传给服务端用以展示
	 * @return
	 */
	public List<Basic_information> findBasicRandom() {
		Connection con = null;
		PreparedStatement pstm = null;
		List<Basic_information> basics = new ArrayList<>();
		try {
			String sql=" ";
			int num=8;

			con = DBUtil.getCon();
			while(num>0) {
				if(8==num)
					sql="select * from search_home where id=(select max(id) from search_home)";
				else if(7==num)
					sql="select * from search_person where id=(select max(id) from search_person)";
				else if(6==num)
					sql="select * from search_vagrancy where id=(select max(id) from search_vagrancy)";
				else if(5==num)
					sql="select * from other_search where id=(select max(id) from other_search)";
				else if(4==num)
					sql="select * from search_home order by rand() limit 1";
				else if(3==num)
					sql="select * from search_person order by rand() limit 1";
				else if(2==num)
					sql="select * from search_vagrancy order by rand() limit 1";
				else if(1==num)
					sql="select * from other_search order by rand() limit 1";
				pstm = con.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery();
				while(rs.next()) {
					String basic_id = rs.getString(1);
					String basic_name = rs.getString(2);
					String basic_sex = rs.getString(3);//性别QAQ
					String basic_photo=rs.getString("photo1");
					//合并时需要改成photo1

					Basic_information basic=new Basic_information();
					basic.setId(basic_id);
					basic.setName(basic_name);
					basic.setSex(basic_sex);
					basic.setPhoto(basic_photo);
					basics.add(basic);//将从数据库中查找的所有用户信息放进users列表中
				}
				--num;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con);
		}
		return basics;

	}
	/**
	 * 从数据库中取全部数据出来
	 * @return
	 */
	public List<Basic_information> findBasicAll() {
		Connection con = null;
		PreparedStatement pstm = null;
		List<Basic_information> basics = new ArrayList<>();
		try {
			String sql=" ";
			int num=4;
			con = DBUtil.getCon();
			while(num>0) {//从每张表中取全部数据放到arraylist里
				if(4==num)
					sql="select * from search_home";
				else if(3==num)
					sql="select * from search_person";
				else if(2==num)
					sql="select * from search_vagrancy ";
				else if(1==num)
					sql="select * from other_search ";
				pstm = con.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery();
				while(rs.next()) {
					String basic_id = rs.getString(1);
					String basic_name = rs.getString(2);
					String basic_sex = rs.getString(3);//性别QAQ
					String basic_photo=rs.getString("photo1");
					//合并时需要改成photo1

					Basic_information basic=new Basic_information();
					basic.setId(basic_id);
					basic.setName(basic_name);
					basic.setSex(basic_sex);
					basic.setPhoto(basic_photo);
					basics.add(basic);//将从数据库中查找的所有用户信息放进users列表中
				}
				--num;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con);
		}
		return basics;

	}
	/**
	 * 计算四张表中共有多少数据
	 * @return 总数
	 */
	public  int findBasicCount() {
		Connection con = null;
		PreparedStatement pstm = null;
		int sum=0;//总数
		try {
			String sql=" ";
			int num=4;
			con = DBUtil.getCon();
			while(num>0) {
				if(4==num)
					sql="select * from search_home";
				else if(3==num)
					sql="select * from search_person";
				else if(2==num)
					sql="select * from search_vagrancy ";
				else if(1==num)
					sql="select * from other_search ";
				pstm = con.prepareStatement(sql);
				ResultSet rs = pstm.executeQuery();
				while(rs.next()) {
					sum+=1;
				}
				--num;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con);
		}
		return sum;

	}

	/**
	 * 取6个数据（每一页展示6个card）返回
	 * 在num位置取size个位置
	 * @param id
	 * @param num
	 * @param size
	 * @return
	 */
	public List<Basic_information> getJspCounrt(int nums,int size) {//取任意6个数据出来 ，不知道这么写任意6个不重复的QAQ
		//其实这个nums和size都没什么用QAQ
		Connection con = null;
		PreparedStatement pstm = null;
		List<Basic_information> basics = new ArrayList<>();
		try {
			String sql=" ";
			if(1==nums){//如果是第一页的话前面几个就放最新的数据
				int num=6;
				con = DBUtil.getCon();
				while(num>0) {//从四张表中任选6组数据
					if(6==num)
						sql="select * from search_home where id=(select max(id) from search_home)";
					else if(5==num)
						sql="select * from search_person where id=(select max(id) from search_person)";
					else if(4==num)
						sql="select * from search_vagrancy where id=(select max(id) from search_vagrancy)";
					else if(3==num)
						sql="select * from other_search where id=(select max(id) from other_search)";
					else if(2==num)
						sql="select * from search_home order by rand() limit 1";
					else if(1==num)
						sql="select * from search_person order by rand() limit 1";
					pstm = con.prepareStatement(sql);
					ResultSet rs = pstm.executeQuery();
					while(rs.next()) {
						String basic_id = rs.getString(1);
						String basic_name = rs.getString(2);
						String basic_sex = rs.getString(3);//性别QAQ
						String basic_photo=rs.getString("photo1");
						//合并时需要改成photo1

						Basic_information basic=new Basic_information();
						basic.setId(basic_id);
						basic.setName(basic_name);
						basic.setSex(basic_sex);
						basic.setPhoto(basic_photo);
						basics.add(basic);//将从数据库中查找的所有用户信息放进users列表中
					}
					--num;
				}
			}
			else {
				System.out.println("其他页面");
				int num=4;
				con = DBUtil.getCon();
				while(num>0) {//从四张表中任选6组数据
					 if(4==num)
						sql="select * from search_home order by rand() limit 2";
					else if(3==num)
						sql="select * from search_person order by rand() limit 2";
					else if(2==num)
						sql="select * from search_vagrancy order by rand() limit 1";
					else if(1==num)
						sql="select * from other_search order by rand() limit 1";
					pstm = con.prepareStatement(sql);
					ResultSet rs = pstm.executeQuery();
					while(rs.next()) {
						String basic_id = rs.getString(1);
						String basic_name = rs.getString(2);
						String basic_sex = rs.getString(3);//性别QAQ
						String basic_photo=rs.getString("photo1");
						//合并时需要改成photo1

						Basic_information basic=new Basic_information();
						basic.setId(basic_id);
						basic.setName(basic_name);
						basic.setSex(basic_sex);
						basic.setPhoto(basic_photo);
						basics.add(basic);//将从数据库中查找的所有用户信息放进users列表中
					}
					--num;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(con);
		}
		return basics;
	}

}
