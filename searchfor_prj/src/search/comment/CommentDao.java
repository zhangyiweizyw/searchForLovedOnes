package search.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import search.entity.PageText;
import search.util.DBUtil;


public class CommentDao {

	public List<Comment> getComments() {
		List<Comment> comments =new ArrayList<>();
		Connection con=null;
		try {
			con = DBUtil.getCon();
			Statement statement = con.createStatement();
			String sql = "select * from comment";
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				Comment comment=new Comment(rs.getInt("id"),rs.getString("name"),rs.getString("tel"),rs.getString("email"),rs.getString("content")
	       	 			,rs.getString("qq"),rs.getString("time"));
				
				comments.add(comment);
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}
	
	public void setComment(String name,String tel,String email,String content,String qq,String time){
		Connection con=null;
		try {
			con = DBUtil.getCon();
			String sql = "insert into comment(name,tel,email,content,qq,time) values(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,name);
			ps.setString(2, tel);
			ps.setString(3, email);
			ps.setString(4, content);
			ps.setString(5, qq);
			ps.setString(6, time);
			ps.executeUpdate();
			ps.close();
			con.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<Comment> getJsp(int num,int size) {
		List<Comment> texts =new ArrayList<>();
		Connection con=null;
		PreparedStatement pstm = null;
		try {
			con = DBUtil.getCon();
			String sql = "select * from comment limit ?,?";
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, (num-1)*size);
			pstm.setInt(2, size);
			ResultSet rs = pstm.executeQuery();
			if(rs.wasNull()==true) {
			}else {
			while(rs.next()) {
				Comment com=new Comment();
				com.setId(Integer.parseInt(rs.getString("id")));
				com.setName(rs.getString("name"));
				com.setContent(rs.getString("content"));
				com.setTime(rs.getString("time"));
				texts.add(com);
			}
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return texts;
	}
	
	public int count() {
		int count = 0;
		Connection con=null;
		PreparedStatement pstm = null;
		try {
			con = DBUtil.getCon();
			String sql = "select * from comment";
			pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				count++;
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
}
