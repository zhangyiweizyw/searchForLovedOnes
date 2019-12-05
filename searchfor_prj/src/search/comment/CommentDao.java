package search.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


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
	
}
